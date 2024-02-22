# MangaHub

## Documentación de Postman
https://documenter.getpostman.com/view/32189050/2sA2r6XQCX

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
