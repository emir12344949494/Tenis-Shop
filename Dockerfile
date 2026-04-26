# --- ESTADIO 1: Compilación ---
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /home/delta/app

# Copiar archivos de configuración de Gradle primero
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties .

# Permisos y dependencias
RUN chmod +x gradlew
RUN ./gradlew --no-daemon dependencies

# Copiar código y compilar
COPY src src
# Limpiamos y generamos el ShadowJar
RUN ./gradlew --no-daemon clean shadowJar

# Verificación (Si el build falla en el COPY, revisa los logs de este comando)
RUN ls -l build/libs/

# --- ESTADIO 2: Imagen Final de Ejecución ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Seguridad: Usuario no-root
RUN addgroup -S devsecgroup && adduser -S delta -G devsecgroup

# CORRECCIÓN: Copiamos cualquier JAR generado en la carpeta libs
# Esto evita errores si el nombre no termina exactamente en "-all.jar"
COPY --from=build /home/delta/app/build/libs/*.jar app.jar

# Si usas el archivo ml5.html, descomenta la siguiente línea:
# COPY ml5.html .

# Ajustar permisos
RUN chown -R delta:devsecgroup /app
USER delta

# Ejecución optimizada
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
