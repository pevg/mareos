# Sistema de Gestión de Envíos

Este proyecto es un sistema de gestión de envíos desarrollado en Java utilizando Spring Boot y JPA (Hibernate). El sistema permite la gestión de clientes, productos, envíos, y tareas asociadas a los envíos, con operaciones como la creación, actualización y consulta de estos elementos. El sistema también incluye endpoints para obtener reportes, como los productos más enviados.

## Tabla de Contenidos

- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Configuración del Entorno](#configuración-del-entorno)
- [Endpoints Principales](#endpoints-principales)
- [Ejecución del Proyecto](#ejecución-del-proyecto)
- [Pruebas](#pruebas)
- [Consideraciones Adicionales](#consideraciones-adicionales)
- [Instalación](#instalación)

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación.
- **Spring Boot**: Framework para el desarrollo de aplicaciones basadas en Java.
- **Spring Data JPA (Hibernate)**: Para la gestión de persistencia.
- **PostgreSQL**: Base de datos relacional.
- **Swagger**: Para la documentación de la API.
- **Maven**: Herramienta de gestión y construcción del proyecto.
- **Docker y Docker Compose**: Para la contenedorización y orquestación de servicios.

## Arquitectura del Proyecto

El proyecto sigue una arquitectura basada en capas:

1. **Entidades**: Representan las tablas de la base de datos y las relaciones entre ellas.
2. **DTOs**: Objetos de transferencia de datos utilizados para la comunicación entre la API y el cliente.
3. **Repositorios**: Interfaces que permiten la interacción con la base de datos utilizando Spring Data JPA.
4. **Servicios**: Contienen la lógica de negocio y coordinan las operaciones entre repositorios y controladores.
5. **Controladores**: Proveen los endpoints de la API REST.
6. **Excepciones**: Manejo de errores específicos de la lógica de negocio.
7. **Enums**: Definición de estados y valores constantes utilizados en el sistema.

## Configuración del Entorno

### Requisitos Previos

- **Java 17**
- **Maven**
- **Docker y Docker Compose**

### Variables de Entorno

El sistema requiere las siguientes variables de entorno para su configuración:

- `SPRING_DATASOURCE_URL`: URL de la base de datos PostgreSQL.
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos.
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos.

Estas variables están configuradas en el archivo `docker-compose.yml`:

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/mareos
  SPRING_DATASOURCE_USERNAME: admin
  SPRING_DATASOURCE_PASSWORD: admin
```

### Instalación

### Usando Docker y Docker Compose

La forma más sencilla y rápida de levantar el proyecto es utilizando Docker y Docker Compose. Esto asegurará que todas las dependencias, como la base de datos PostgreSQL, se configuren automáticamente.

1. **Clonar el repositorio**:

   ```sh
   git clone https://github.com/pevg/mareos.git
   cd mareos
   ```

2. **Compilar el Proyecto**:

   - Abre una terminal y navega al directorio raíz del proyecto.
   - Ejecuta el siguiente comando para compilar y empaquetar el proyecto en un archivo `WAR`:

   ```sh
   mvn clean package -DskipTests
   ```

   Esto generará el archivo `WAR` en el directorio `target/` como `envios-0.0.1-SNAPSHOT.war`.

3. **Desplegar en un Servidor de Aplicaciones de tu Preferencia**:

   - Usa el IDE de tu preferencia (como IntelliJ IDEA, Eclipse, o NetBeans) para desplegar el archivo `WAR` en un servidor de aplicaciones compatible con Java EE, como Apache Tomcat, Jetty, o WildFly.
   - Sigue las instrucciones del IDE para configurar y ejecutar el servidor de aplicaciones, asegurándote de que el archivo `WAR` esté desplegado correctamente.

4. **Acceder a la Aplicación**:

   - La aplicación estará disponible en `http://localhost:8080` (o en el puerto y contexto configurados en tu servidor de aplicaciones).
   - La documentación de la API estará disponible en `http://localhost:8080/swagger-ui.html`.

### Inicialización de la Base de Datos

Si estás utilizando Docker, un contenedor adicional (`db_init`) ejecutará un script SQL (`init.sql`) para inicializar la base de datos con datos básicos.

Si levantas el proyecto manualmente, tendrás que ejecutar este script manualmente antes de utilizar la aplicación.

Estos pasos te permitirán levantar el proyecto de manera rápida, ya sea utilizando Docker para una configuración automatizada o configurando manualmente los servicios necesarios con el IDE de tu elección.
