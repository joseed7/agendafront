# 📱 Agenda Web - Spring Boot + Servlets + JSP

Una aplicación web completa para gestión de contactos desarrollada con Spring Boot 3.2.0, Servlets y JSP, implementando una arquitectura MVC moderna con transacciones multi-entidad.

## 🚀 Características

- **CRUD Completo**: Crear, leer, actualizar y eliminar contactos
- **Interfaz Web Moderna**: Bootstrap 5 + JSP
- **Arquitectura MVC**: Separación clara de responsabilidades
- **Base de Datos H2**: Configuración en memoria para desarrollo
- **Transacciones JPA**: Preparado para operaciones multi-entidad
- **Validaciones**: Cliente y servidor integradas

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Spring Boot | 3.2.0 | Framework principal |
| Java | 17 | Lenguaje de programación |
| Jakarta EE | 9+ | Servlets, JSP, JPA |
| H2 Database | 2.1.214 | Base de datos en memoria |
| Bootstrap | 5.1.3 | Framework CSS |
| JPA/Hibernate | 6.1+ | Persistencia ORM |
| Gradle | 8.5 | Sistema de construcción |

## 📁 Estructura del Proyecto
src/main/java/com/agendafront/
├── servlets/ # Controladores Web
│ ├── ContactoServlet.java # Listar y crear contactos
│ └── ContactoCrudServlet.java # Editar y eliminar contactos
├── entities/ # Entidades JPA
│ └── Contacto.java # Entidad principal
├── service/ # Lógica de negocio
│ └── ContactoService.java # Servicios transaccionales
├── repository/ # Acceso a datos
│ └── ContactoRepository.java # Repositorio JPA
└── AgendaApplication.java # Clase principal

src/main/webapp/
└── WebInfo/
└── views/
└── contactos/
├── lista.jsp # Vista listar contactos
└── editar.jsp # Vista editar contacto

Acceso
Aplicación: http://localhost:8080/contactos

Consola H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuario: sa

Password: (vacío)

🎯 Endpoints Principales
Método	URL	Descripción
GET	/contactos	Listar todos los contactos
POST	/contactos	Crear nuevo contacto
GET	/contactos/editar/{id}	Formulario edición contacto
GET	/contactos/eliminar/{id}	Eliminar contacto

🔧 Configuración Principal
application.properties

# Servidor
server.port=8080

# Base de datos H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Vistas JSP
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp


build.gradle

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'com.h2database:h2'
    implementation 'jakarta.servlet:jakarta.servlet-api:6.0.0'
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
}

🚀 Funcionalidades Implementadas
Operaciones CRUD
Listar contactos con paginación preparada

Crear contactos con validación de formularios

Editar contactos con formularios pre-cargados

Eliminar contactos con confirmación

Interfaz de Usuario
Diseño responsive con Bootstrap 5

Validación en tiempo real en formularios

Mensajes de feedback para el usuario

Navegación intuitiva entre vistas

Características Técnicas
Manejo de excepciones global

Logging de consultas SQL

Configuración de perfiles (dev/prod)

Arquitectura escalable para nuevas funcionalidades
