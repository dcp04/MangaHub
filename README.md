# MangaHub

## Documentación de Postman
https://documenter.getpostman.com/view/32189050/2sA2r6XQCX

# Objetivo y Público

MangaHub es una aplicación diseñada para los entusiastas del manga, que ofrece una amplia variedad de mangas de todo el mundo, incluyendo aquellos que no son tan conocidos pero que cuentan con historias magníficas. Nuestra plataforma se enfoca en brindar a los usuarios una experiencia única y efectiva para descubrir y disfrutar de estos emocionantes mundos narrativos.

## Características Principales

- **Amplia Biblioteca de Mangas:** Ofrecemos una extensa colección de mangas de diversos géneros y estilos, desde clásicos hasta obras más contemporáneas.

- **Descubrimiento Personalizado:** Utilizando algoritmos avanzados, ofrecemos recomendaciones personalizadas para cada usuario, ayudándoles a descubrir mangas que se adapten a sus gustos y preferencias.

- **Entrenamiento Personalizado:** MangaHub ofrece mangas personalizados diseñados para ayudar a los usuarios a mejorar su experiencia de lectura y descubrimiento de mangas.

## Actores

Los actores de la aplicación son:

| Nombre            | Descripción                                                                                                                                                                 |
|-------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Administrador     | - Responsable de gestionar y mantener el sistema de MangaHub.<br>- Creación, eliminación y gestión de cuentas de usuario.<br>- Revisión y moderación del contenido.<br>- Actualización y mantenimiento del sistema. |
| Usuario Registrado| - Persona que ha creado una cuenta en MangaHub.<br>- Puede acceder a entrenamientos personalizados, realizar un seguimiento de su progreso y participar en programas específicos.           |
| Usuario Anónimo   | - Persona que navega por MangaHub sin haber creado una cuenta.<br>- Puede explorar información general sobre los servicios ofrecidos y obtener una vista previa de las funcionalidades.          |

## Casos De Uso

### CU1: Registrarse

**Actor:** Usuario Anónimo

**Descripción:** Crear una cuenta en la aplicación proporcionando información básica y preferencias de entrenamiento.

### CU2: Iniciar Sesión

**Actor:** Usuario Registrado, Administrador

**Descripción:** Acceder a la cuenta utilizando credenciales previamente registradas.

### CU3: Administrar Contenido

**Actor:** Administrador

**Descripción:** Agregar, editar o eliminar mangas, así como supervisar y gestionar contenido general de la aplicación.

### CU4: Reservar Manga

**Actor:** Usuario Registrado

**Descripción:** Reservar mangas con su id.

### CU5: Explorar Mangas

**Actor:** Usuario Anónimo, Usuario Registrado

**Descripción:** Ver la lista de mangas disponibles.

### CU6: Filtrar Mangas

**Actor:** Usuario Registrado

**Descripción:** Filtrar mangas por título, autor o ambos.



## Descripción
Mangahub es una aplicación para la gestión de mangas y reservas. Permite a los usuarios explorar y reservar mangas, así como autenticarse de forma segura.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes y clases:

### Configuración
- `mangahub.app.config.InitializationData`: Clase para la inicialización de datos.
- `mangahub.app.config.JwtAuthenticationFilter`: Filtro de autenticación JWT.
- `mangahub.app.config.SecurityConfiguration`: Configuración de seguridad.

### Controladores
- `mangahub.app.controller.AuthorizationController`: Controlador para autorización.
- `mangahub.app.controller.MangaController`: Controlador para operaciones relacionadas con mangas.
- `mangahub.app.controller.user.AuthenticationController`: Controlador para autenticación de usuarios.
- `mangahub.app.controller.user.AuthorizationAdminController`: Controlador para autorización de administradores.

### DTO (Data Transfer Objects)
#### Solicitudes
- `mangahub.app.dto.request.SigninRequest`: DTO para solicitudes de inicio de sesión.
- `mangahub.app.dto.request.SignUpRequest`: DTO para solicitudes de registro de usuario.
#### Respuestas
- `mangahub.app.dto.response.user.JwtAuthenticationResponse`: DTO para respuestas de autenticación JWT.
- `mangahub.app.dto.response.user.UsuarioResponse`: DTO para respuestas de usuario.

### Entidades
- `mangahub.app.entities.EstadoReserva`: Enumeración para el estado de reserva.
- `mangahub.app.entities.Manga`: Clase para la entidad Manga.
- `mangahub.app.entities.Reserva`: Clase para la entidad Reserva.
- `mangahub.app.entities.Role`: Clase para la entidad Role.
- `mangahub.app.entities.Usuario`: Clase para la entidad Usuario.

### Manejo de Errores
- `mangahub.app.error.GlobalExceptionHandler`: Manejador global de excepciones.
#### Excepciones
- `mangahub.app.error.exception.MangaNotFoundException`: Excepción para manga no encontrado.
- `mangahub.app.error.exception.UserNotFoundException`: Excepción para usuario no encontrado.

### Repositorios
- `mangahub.app.repository.MangaRepository`: Repositorio para la entidad Manga.
- `mangahub.app.repository.ReservaRepository`: Repositorio para la entidad Reserva.
- `mangahub.app.repository.UserRepository`: Repositorio para la entidad Usuario.

### Servicios
- `mangahub.app.service.MangasService`: Interfaz para el servicio de mangas.
- `mangahub.app.service.UserService`: Interfaz para el servicio de usuarios.
#### Implementaciones
- `mangahub.app.service.impl.MangasServiceImpl`: Implementación del servicio de mangas.
- `mangahub.app.service.impl.ReservaServiceImpl`: Implementación del servicio de reservas.

### Servicios de Usuario
- `mangahub.app.service.user.AuthenticationService`: Interfaz para el servicio de autenticación.
- `mangahub.app.service.user.JwtService`: Interfaz para el servicio JWT.
- `mangahub.app.service.user.ReservaService`: Interfaz para el servicio de reservas de usuario.
#### Implementaciones
- `mangahub.app.service.user.impl.AuthenticationServiceImpl`: Implementación del servicio de autenticación.
- `mangahub.app.service.user.impl.JwtServiceImpl`: Implementación del servicio JWT.
- `mangahub.app.service.user.impl.UserServiceImpl`: Implementación del servicio de usuario.
