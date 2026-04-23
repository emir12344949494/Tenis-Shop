package mx.edu.uttt

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.staticfiles.Location
import io.javalin.vue.VueComponent
import com.mongodb.client.MongoClients
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Filters
import org.bson.Document
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.MailerBuilder
import org.simplejavamail.api.mailer.config.TransportStrategy

fun main() {
    // 1. Conexión a MongoDB Atlas
    val mongoClient = MongoClients.create("mongodb+srv://dayronemirgaliciacaballero_db_user:ul8A39CPEMcb5Vtt@cluster0.eis0shs.mongodb.net/")
    val database = mongoClient.getDatabase("Tenis_Registro")
    val usersCollection = database.getCollection("Tenis")

    val app = Javalin.create { config ->
        config.staticFiles.enableWebjars()
        config.staticFiles.add("public", Location.CLASSPATH)
        config.vue.vueInstanceNameInJs = "app"
        config.vue.rootDirectory("/vue", Location.CLASSPATH)

        config.router.mount {
        }.apiBuilder {
            // Vistas Vue
            get("/", VueComponent("homepage"))
            get("login", VueComponent("login-page"))
            get("administrador", VueComponent("admin"))
            get("Empleado", VueComponent("empleado"))
            get("registro", VueComponent("retro"))

            path("api/auth") {
                post("step1") { ctx ->
                    try {
                        val body = ctx.bodyAsClass(Map::class.java)
                        val email = body["email"] as? String
                        val ticket = body["ticket"] as? Map<*, *>

                        if (email != null) {
                            val generatedCode = (100000..999999).random().toString()

                            if (ticket != null) {
                                val compraDoc = Document("email", email)
                                    .append("producto", ticket["producto"])
                                    .append("precio", ticket["precio"])
                                    .append("tipo", ticket["tipo"])
                                    .append("cliente", ticket["cliente"])
                                    .append("fecha_ticket", ticket["fecha"])
                                    .append("fecha_servidor", java.util.Date())
                                    .append("status", "completado")

                                usersCollection.insertOne(compraDoc)
                                ctx.json(mapOf("status" to "success", "message" to "Venta registrada"))
                            } else {
                                val userUpdate = Document("email", email)
                                    .append("authCode", generatedCode)
                                    .append("status", "pending_2fa")
                                    .append("last_login", java.util.Date())

                                usersCollection.updateOne(
                                    Filters.eq("email", email),
                                    Document("\$set", userUpdate),
                                    UpdateOptions().upsert(true)
                                )

                                println("\n[SISTEMA] Código generado para $email: $generatedCode\n")
                                ctx.json(mapOf("status" to "success", "message" to "Código generado"))
                            }
                        } else {
                            ctx.status(400).json(mapOf("status" to "error", "message" to "Email requerido"))
                        }
                    } catch (e: Exception) {
                        ctx.status(500).json(mapOf("status" to "error", "message" to e.message))
                    }
                }

                post("verify") { ctx ->
                    val body = ctx.bodyAsClass(Map::class.java)
                    val emailUsuario = body["email"] as? String
                    val code = body["code"] as? String

                    val user = usersCollection.find(Filters.eq("email", emailUsuario)).first()

                    if (user != null && user.getString("authCode") == code) {
                        // --- LÓGICA DE NOTIFICACIÓN MEJORADA ---
                        try {
                            val miCorreo = "tu_correo@gmail.com" // TU GMAIL REAL
                            val miPasswordApp = "xxxx xxxx xxxx xxxx" // TU CLAVE DE 16 LETRAS

                            val emailNotif = EmailBuilder.startingBlank()
                                .from("SneakerShop UTTT", miCorreo)
                                .to("Estudiante UTTT", emailUsuario!!)
                                .withSubject("Acceso Confirmado - StockX")
                                .withPlainText("Hola. Confirmamos que has accedido a tu cuenta de SneakerShop desde el ThinkPad.")
                                .buildEmail()

                            val mailer = MailerBuilder
                                .withSMTPServer("smtp.gmail.com", 587, miCorreo, miPasswordApp)
                                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                                // Debug para ver errores en la terminal de Linux
                                .withDebugLogging(true)
                                .buildMailer()

                            // Envío asíncrono
                            mailer.sendMail(emailNotif, true)
                            println("[CORREO] Intentando enviar notificación a $emailUsuario...")

                        } catch (mailEx: Exception) {
                            println("[ERROR MAIL] No se pudo enviar el correo: ${mailEx.message}")
                            mailEx.printStackTrace()
                        }

                        ctx.json(mapOf("status" to "authorized"))
                    } else {
                        ctx.status(401).json(mapOf("status" to "denied", "message" to "Código incorrecto"))
                    }
                }
            }
        }
    }.start()
}