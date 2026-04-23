plugins {
    kotlin("jvm") version "2.0.21"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "mx.edu.uttt"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://www.webjars.org/maven") }
}

dependencies {
    /* 1. Servidor y JSON (Javalin 6) */
    implementation("io.javalin:javalin:6.3.0")
    implementation("io.javalin:javalin-rendering:6.3.0")
    implementation("org.slf4j:slf4j-simple:2.0.12")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    /* 2. Frontend (Versiones estables verificadas) */
    implementation("org.webjars.npm:vue:3.4.21")
    implementation("org.webjars.npm:vuetify:3.10.5")
    implementation("org.webjars.npm:mdi__font:7.4.47")
    implementation("org.webjars:font-awesome:6.5.1")
    implementation("org.webjars.npm:sweetalert2:11.12.4")

    /* 3. Base de Datos (Driver oficial de MongoDB, NO Spring Boot) */
    // Esto reemplaza a los starters de Spring que daban error
    implementation("org.mongodb:mongodb-driver-sync:4.11.1")
    implementation("org.simplejavamail:simple-java-mail:8.11.1")
}

kotlin {
    jvmToolchain(21)
}

application {
    // IMPORTANTE: Asegúrate de que coincida con tu paquete en el Main.kt
    mainClass.set("mx.edu.uttt.MainKt")
}

tasks {
    // Usamos ShadowJar en lugar del bloque manual de Jar para evitar errores de duplicados
    shadowJar {
        archiveClassifier.set("")
        manifest {
            attributes("Main-Class" to "mx.edu.uttt.MainKt")
        }
        mergeServiceFiles() // Crítico para que Javalin y Jackson funcionen juntos
    }

    // Desactivamos el JAR estándar para que no haya conflictos
    jar {
        enabled = false
    }

    build {
        dependsOn(shadowJar)
    }
}