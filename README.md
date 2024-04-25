# Gestión de Libros

Este proyecto es una aplicación de gestión de libros desarrollada en Java utilizando el framework Spring. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una base de datos de libros, como agregar nuevos libros, modificar los existentes y eliminar los que ya no son necesarios.

## Características

- Agregar libros nuevos especificando su nombre, autor, precio y existencias.
- Ver una lista de todos los libros existentes en la base de datos.
- Seleccionar un libro de la lista para modificar sus detalles.
- Eliminar un libro de la base de datos.

## Tecnologías Utilizadas

- Java
- Swing
- Spring Framework
- Spring Boot
- Spring Data JPA
- Lombok
- MySQL

## Estructura del Proyecto

El proyecto está estructurado en tres paquetes principales:

- **com.ulloalibros.vista:** Contiene las clases relacionadas con la interfaz de usuario, incluyendo la ventana principal de la aplicación y la lógica para interactuar con el usuario.
- **com.ulloalibros.servicio:** Contiene las clases que proporcionan la lógica de negocio de la aplicación, incluyendo la gestión de libros.
- **com.ulloalibros.modelo:** Contiene las clases que definen el modelo de datos de la aplicación, incluyendo la entidad Libro.

## Configuración y Ejecución

Para ejecutar la aplicación localmente, sigue estos pasos:

1. Clona el repositorio a tu máquina local.
2. Abre el proyecto en tu IDE preferido.
3. Ejecuta la clase principal `com.ulloalibros.vista.LibroForm` para iniciar la aplicación.
4. La aplicación se abrirá y podrás comenzar a gestionar los libros.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir al proyecto, por favor crea un pull request con tus cambios propuestos.

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).

