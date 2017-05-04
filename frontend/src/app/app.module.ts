import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';

import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { IndexComponent } from './index.component';

// Hijos de IndexComponent
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
// Fin Hijos de IndexComponent

// Login
import { LoginService } from './login.service';
import { LoginComponent } from './login.component';

// Users
import { UsersService } from './users.service';
import { UserMyAccountComponent } from './user.myaccount.component';
import { UserFavouritesComponent } from './user.favourites.component';
import { UserMyRecipesComponent } from './user.myrecipes.component';
import { UserAllRecipesComponent } from './user.allrecipes.component';
import { UserMyRestaurantsComponent } from './user.myRestaurants.component';
import { UserAllRestaurantsComponent } from './user.allRestaurants.component';
import { UserMyEventosComponent } from './user.myEventos.component';
import { UserAllEventosComponent } from './user.allEventos.component';
import { UserAllUsersComponent } from './user.allUsers.component';
import { UserPreferencesComponent } from './user.preferences.component';
import { UserPublicProfileComponent } from './user.publicprofile.component';

// Hijos de UsersService
import { UserMenuComponent } from './user.menu.component';
// Fin Hijos de UsersService

// Recipes
import { RecipesService } from './recipes.service';
import { RecipesComponent } from './recipes.component';
import { RecipeComponent } from './recipe.component';
import { RecipeFormComponent } from './recipe-form.component';
import { RecipesSearchComponent } from './recipes.search.component';
import { RecipesIngredientsComponent } from './recipes.ingredients.component';
import { RecipesTypesFoodComponent } from './recipes.typesfood.component';

// Restaurants
import { RestaurantsService } from './restaurants.service';
import { RestaurantsComponent } from './restaurants.component';
import { RestaurantComponent } from './restaurant.component';
import { RestaurantFormComponent } from './restaurant-form.component';
import { RestaurantsTypeFoodComponent } from './restaurants-typeFood.component';

//Eventos
import { EventosService } from './eventos.service';
import { EventosComponent } from './eventos.component';
import { EventoComponent } from './evento.component';
import { EventoFormComponent } from './evento-form.component';

import { FroalaEditorModule, FroalaViewModule } from 'angular2-froala-wysiwyg';
//RECETA

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    // User
    UserMyAccountComponent,
    UserFavouritesComponent,
    UserMenuComponent,
    UserMyRecipesComponent,
    UserMyRestaurantsComponent,
    UserMyEventosComponent,
    UserPreferencesComponent,
    UserAllRecipesComponent,
    UserAllRestaurantsComponent,
    UserAllEventosComponent,
    UserAllUsersComponent,
    UserPublicProfileComponent,
    // Recipes
    RecipesComponent,
    RecipeComponent,
    RecipeFormComponent,
    RecipesSearchComponent,
    RecipesIngredientsComponent,
    RecipesTypesFoodComponent,
    // Restaurants
    RestaurantsComponent,
    RestaurantComponent,
    RestaurantFormComponent,
    RestaurantsTypeFoodComponent,
    // Eventos
    EventosComponent,
    EventoComponent,
    EventoFormComponent,
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    routing,
    NgbCollapseModule.forRoot(),
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot()
  ],
  providers: [UsersService, LoginService, RecipesService, RestaurantsService, EventosService],
  bootstrap: [AppComponent]
})

export class AppModule { }
