# API DE COOKCINANDO


## Aspectos comunes

Estados que utilizamos:

- **401 UNAUTHORIZED:** cuando el usuario que intenta acceder no está autorizado.
- **200 OK:** se ha completado con éxito.
- **404 NOT_FOUND:** cuando el contenido al que se quiere acceder no se encuentra en la base de datos.
- **403 FORBIDDEN:** acceso denegado a una url por acceder desde un usuario sin permisos.

## Recetas

- Obtener todas las recetas:
    - URL: /api/recipes/ 
    - Método: GET
    - Salida: Todas las recetas paginadas

- Obtener una receta:
    - URL: /api/recipes/{id}
    - Método: GET
    - Salida: Receta

- Crear receta:
    - URL: /api/recipes/ 
    - Método: POST
    - Body: Receta
    - Salida: Receta

- Editar receta
    - URL: /api/recipes/{id}
    - Método: PUT
    - Body: Receta
    - Salida: Receta

- Eliminar receta
    - URL: /api/recipes/{id}
    - Método: DELETE
    - Salida: Confirmación
 
- Recetas recomendadas
    - URL: /api/recipes/recommended
    - Método: GET
    - Salida: Lista de tres recetas


## Restaurantes

- Obtener todos los restaurantes
    - URL: /api/restaurants/ 
    - Método: GET
    - Salida: Todos los restaurantes paginados

- Obtener un restaurante
    - URL: /api/restaurants/{id}
    - Método: GET
    - Salida: Restaurante

- Crear restaurante
    - URL: /api/restaurants/ 
    - Método: POST
    - Body: Restaurante
    - Salida: Restaurante

- Editar restaurante
    - URL: /api/restaurants/{id}
    - Método: PUT
    - Body: Restaurante
    - Salida: Restaurante

- Eliminar restaurante
    - URL: /api/restaurants/{id}
    - Método: DELETE
    - Salida: Confirmación

- Restaurantes recomendados
    - URL: /api/restaurants/recommended
    - Método: GET
    - Salida: Lista de tres restaurantes

## Eventos

- Obtener todos los eventos
 : URL: /api/events/ 
 : Método: GET
 : Salida: Todos los eventos

- Obtener un evento
    - URL: /api/events/{id}
    - Método: GET
    - Salida: Evento

- Crear evento
    - URL: /api/events/ 
    - Método: POST
    - Body: Evento
    - Salida: Evento

- Editar evento
    - URL: /api/events/{id}
    - Método: PUT
    - Body: Evento
    - Salida: Evento

- Eliminar evento
    - URL: /api/events/{id}
    - Método: DELETE
    - Salida: Confirmación

- Eventos recomendados
    - URL: /api/events/recommended
    - Método: GET
    - Salida: Lista de tres eventos

## Usuarios

- Obtener todos los usuarios
    - URL: /api/users/ 
    - Método: GET
    - Salida: Colección de todos los usuarios

- Obtener un usuario
    - URL: /api/users/{id}
    - Método: GET
    - Salida: Usuario

- Obtener un las recetas favoritas de un usuario
    - URL: /api/users/{id}/favorite-recipes
    - Método: GET
    - Salida: Lista de recetas

- Obtener un los restaurantes favoritos de un usuario
    - URL: /api/users/{id}/favorite-restaurants
    - Método: GET
    - Salida: Lista de restaurantes

- Obtener un los eventos favoritos de un usuario
    - URL: /api/users/{id}/favorite-events
    - Método: GET
    - Salida: Lista de eventos

- Crear usuario
    - URL: /api/users/registry
    - Método: POST
    - Body: Usuario
    - Salida: Usuario

- Editar usuario
    - URL: /api/users/{id}
    - Método: PUT
    - Body: Usuario
    - Salida: Usuario

- Eliminar usuario
    - URL: /api/users/{id}
    - Método: DELETE
    - Salida: Confirmación

## LOGIN

- Login de un usuario
    - URL: /api/logIn
    - Salida: Confirmación

- Logout de un usuario
    - URL: /api/logOut
    - Salida: Confirmación