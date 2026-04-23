# Tenis-Shop

Bienvenido a **Tenis-Shop**! Este es un proyecto de comercio electrónico enfocado en la venta de calzado deportivo, desarrollado como parte de mi formación técnica.


![Captura de pantalla](./src/main/resources/public/images/page.png)

##  Descripción del Proyecto
Tenis-Shop es una aplicación que permite gestionar un catálogo de tenis, visualizando modelos, precios y detalles técnicos. El objetivo es aplicar conocimientos de desarrollo de software, gestión de versiones con Git y despliegue de aplicaciones.

## ️ Tecnologías Utilizadas
* **Lenguaje del Backend:** Kotlin (desarrollado en IntelliJ IDEA)
* **Lenguaje del Fronend:** Vuetify (conplemento con CSS)
* **Control de Versiones:** Git & GitHub
* **Entorno de desarrollo:** Linux (Ubuntu en una instancia de AWS)
* **Base de Datos:** (Mongo DB que  uso com base da datos en el proyecto)
* **Docker:** (Despliegue de aplicacione en la web)
* **Nginx:** (Para el balanceador de carga dentro de la instancia de AWS)

##  Estructura del Repositorio
* `/src`: Código fuente de la aplicación.
* `/main`: Archivos del backend.
* `/test`: Configuraciones del entorno de IntelliJ.
* `/resources`: Archivos del fronend.

##  Seguridad e Infraestructura
Se implementaron múltiples capas de seguridad para proteger la integridad de los datos y la disponibilidad del servicio:

* **Network Security:** Configuración de Grupos de Seguridad (Security Groups) actuando como firewall virtual, permitiendo únicamente tráfico entrante por los puertos 80 (Web) y 22 (Administración restringida).
* **Protección de Credenciales:** Uso de variables de entorno para las cadenas de conexión a la base de datos, evitando la exposición de datos sensibles en el repositorio público.
* **Acceso Seguro:** Autenticación basada en llaves SSH (.pem) para la gestión del servidor EC2.
* **Cifrado:** (Si usaste HTTPS) Implementación de certificados SSL para cifrar el tráfico entre el cliente y el servidor.

## Funcionalidades
- Registro y Login de usuarios.
-  Catálogo dinámico de calzado.
-  Filtros por categoría y precio.
-  Conexión en tiempo real con Base de Datos.