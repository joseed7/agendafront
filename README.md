📱 Agenda App - Spring Boot + Swing
Una aplicación de escritorio moderna para gestión de contactos, desarrollada con Spring Boot y Swing, que combina la potencia del backend Spring con una interfaz gráfica tradicional de Java.

🚀 Características
CRUD Completo: Crear, leer, actualizar y eliminar contactos

Interfaz Gráfica Amigable: Desarrollada con Java Swing

Arquitectura Moderna: Spring Boot + Patrón MVC

Comunicación REST: Cliente Feign para APIs externas

Validación de Datos: Formularios con validación integrada

Persistencia: Modelo de datos relacional preparado para MySQL/PostgreSQL

🛠️ Tecnologías Utilizadas
Tecnología	Versión	Propósito
Java	17	Lenguaje de programación
Spring Boot	3.2.0	Framework backend
Spring Cloud OpenFeign	4.1.0	Cliente HTTP declarativo
Gradle	8.5	Sistema de construcción
Swing	-	Interfaz gráfica de usuario
Lombok	-	Reducción de código boilerplate

⚙️ Configuración e Instalación
Prerrequisitos
Java 17 o superior

Gradle 8.5 o superior

IntelliJ IDEA

Clonar o descargar el proyecto

bash
git clone <url-del-repositorio>
cd agenda-app
Configurar el JDK en IntelliJ

File > Project Structure > Project SDK: Java 17

File > Project Structure > Project language level: 17

Habilitar Annotation Processing

File > Settings > Build > Compiler > Annotation Processors

Marcar "Enable annotation processing"

Ejecutar la aplicación

bash
./gradlew bootRun
O desde IntelliJ: Ejecutar AgendaApplication.java

Configuración de Propiedades
application.properties

properties
server.port=8080
api.contactos.base-url=http://localhost:8080/api
spring.main.web-application-type=SERVLET
feign.client.config.default.loggerLevel=basic
🎯 Uso de la Aplicación
Pantalla Principal
La interfaz se divide en tres secciones principales:

Formulario de Contactos: Campos para ingresar datos del contacto

Tabla de Contactos: Lista todos los contactos existentes

Botones de Acción: Operaciones CRUD

Funcionalidades
Operación	Descripción	Shortcut
Agregar	Crear nuevo contacto	Botón "Agregar"
Editar	Modificar contacto existente	Doble clic en tabla
Eliminar	Borrar contacto seleccionado	Botón "Eliminar"
Actualizar	Refrescar lista de contactos	Botón "Actualizar"
Validaciones
✅ Nombre y apellido obligatorios

✅ Teléfono requerido

✅ Formato de email válido (opcional)

🔧 Desarrollo
Compilación
bash
./gradlew build
Ejecución de Tests
bash
./gradlew test
Generación de JAR ejecutable
bash
./gradlew bootJar

🌐 API Endpoints
La aplicación expone los siguientes endpoints REST:

Método	Endpoint	Descripción
GET	/api/local/contactos	Obtener todos los contactos
GET	/api/local/contactos/{id}	Obtener contacto por ID
POST	/api/local/contactos	Crear nuevo contacto
PUT	/api/local/contactos/{id}	Actualizar contacto
DELETE	/api/local/contactos/{id}	Eliminar contacto

🐛 Solución de Problemas
Error común: "Connection refused"
Síntoma: Error al conectar con localhost:8080
Solución: El backend mock está incluido, verificar que el puerto 8080 esté libre.

Error: "Cannot resolve symbol Lombok"
Solución:

Habilitar annotation processing

File > Invalidate Caches and Restart

La ventana no aparece
Solución: Verificar que java.awt.headless=false esté configurado
