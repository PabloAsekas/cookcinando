# Cookcinando
[VIDEO DEMOSTRACIÓN](https://youtu.be/qCOh1c0Pqz4)


Project coded in HTML, CSS, JS, Spring and Angular

## Description

Cookcinando is a web application that allows the users to watch and to post recipes so the rest of the users could use.

The web's interface shows the different sections and the content that users generate.

The web's private part lets users to modify their profile and include more content (recipies, videos, comments, manage the favorites, etc)

## Entities

- **Recipies:** an entity which contains where you can find all the recipes thar users have posted (public and private).
- **Restaurants:** an entity where you can find the group of differents retaurants that we consider important (public and private).
- **Users:** an entity where you can find the list of registed users who use the web application (public and private).
- **Events:** an entity where you can find all the events which we want to show the users and they can dedice to go or not to go wheter they are interested or not (public and private).
- **Drinks:** an entity where you can find all drinks' recipies that users have posted (public and private).

## Group members

| Nombre | Email | Usuario GitHub |
|--------|-------|----------------|
| María Ballesteros López | m.ballesteroslo@alumnos.urjc.es | mariablopez |
| Pablo Bermejo Gómez     | p.bermejogo@alumnos.urjc.es     | PabloAsekas |
| Ignacio López Lòpez     | i.lopezlope@alumnos.urjc.es     | ilopezlopez |
| Blanca Romero Rico      | b.romerori@alumnos.urjc.es      | bromerori   |

## [API](https://github.com/PabloAsekas/cookcinando/blob/master/API.md)

## Public and private diagrams

Private
![](https://github.com/PabloAsekas/cookcinando/blob/master/diagramaPartePrivada.jpg)

Public
![](https://github.com/PabloAsekas/cookcinando/blob/master/diagramaPartePublica.jpg)

## Main Pages

INDEX: Main page of the web, where you can see a sample of the content.
![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/Nueva%20home.jpg)

REGISTRO/LOGIN - Page through which the private part of the users can be accessed.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/REGISTRO-LOGIN.png)

CUADRO PRIVADO DE USUARIO - Page where the user can manage his account and its contents.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/CUADRO-PRIVADO-USUARIO.png)

RECETAS - Page where you can find all the recipes.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/RECETAS.png)

RESTAURANTES - Page where you can find all restaurants.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/RESTAURANTES.png)

EVENTOS - Page where you can find all the events.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/EVENTOS.png)

PERFIL PÚBLICO - Page where we can meet a user and his content.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/PERFIL-PUBLICO.png)

CREACIÓN DE CONTENIDO - Page for users to create content for the page.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/CREACION-DE-CONTENIDO.png)

VISTA DE CONTENIDO - Page where the content is displayed.
![](https://github.com/PabloAsekas/cookcinando/raw/master/Screenshoots/VISTA-DE-CONTENIDO.png)

## Entity Diagram 

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/diagrama.png)

## Class Diagram and Templates

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/diagramaClases.jpeg)
![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/diagram1.PNG)
![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/diagram2.PNG) 

## API REST Diagram

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/diagramaAPIREST.JPG)

## Frontend Diagrams

- **HEADER**


![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/11header.component.JPG)


- **INDEX**


![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/12index.component.JPG)


- **APP**


App Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/1app.component.JPG)


App Routing

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/3app.routing.JPG)

- **LOGIN**


![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/13login.component.JPG)


- **RECETAS**


Recipe Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/14recipe.component.JPG)

Recipe Model

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/15recipe.model.JPG)

Recipe Form Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/16recipe-form.component.JPG)

Recipes Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/17recipes.component.JPG)

Recipe Search

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/19recipe.search.component.JPG)

Recipes Service

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/20recipes.service.JPG)

Recipes Ingredients

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/18recipes.ingredients.JPG)

Recipes Types of food

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/21recipes.typesfood.JPG)


- **RESTAURANTES**


Restaurant Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/22restaurant.component.JPG)

Restaurant Model

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/23restaurant.model.JPG)

Restaurant Form Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/24restaurant-form.component.JPG)

Restaurants Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/25restaurants.component.JPG)

Restaurants Service

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/26restaurants.service.JPG)

Restaurants Types of food

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/27restaurants.typesfood.component.JPG)


- **EVENTOS**


Event Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/4evento.component.JPG)

Event Model

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/5evento.model.JPG)

Event Form Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/6evento-form.component.JPG)

Events Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/7eventos.component.JPG)

Events Service

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/8eventos.service.JPG)

Events Types of food

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/9eventos.typesfood.component.JPG)


- **USUARIOS**


User all Recipes 

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/29user.allRecipes.component.JPG)

User all Restaurants

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/30user.allRestaurants.component.JPG)

User all Event

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/28user.allEventos.component.JPG)

User component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/31user.allUsers.component.JPG)

User favourites Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/32user.favourites.component.JPG)

User Menu Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/33user.menu.component.JPG)

User Model

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/34user.model.JPG)

User My Account Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/35user.myaccount.component.JPG)

User My Recipes Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/37user.myRecipes.component.JPG)

User My Restaurants

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/38user.myRestaurants.component.JPG)

User My Events

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/36user.myEventos,component.JPG)

User Preferences Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/39user.preferences.component.JPG)

User Public Profile Component

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/40user.publicprofile.component.JPG)

Users Service

![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/41users.service.JPG)


- **FOOTER**


![](https://github.com/PabloAsekas/cookcinando/blob/master/Screenshoots/10footer.component.JPG)
