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
import { UserPreferencesComponent } from './user.preferences.component';
import { UserPublicProfileComponent } from './user.publicprofile.component';

// Hijos de UsersService
import { UserMenuComponent } from './user.menu.component';
// Fin Hijos de UsersService

// Recipes
import { RecipesService } from './recipes.service';
import { RecipesComponent } from './recipes.component'; // TODAS LAS RECETAS
import { RecipeComponent } from './recipe.component';   // UNA RECETA
import { RecipeFormComponent } from './recipe-form.component';   // FORM DE UNA 

import { FroalaEditorModule, FroalaViewModule } from 'angular2-froala-wysiwyg';
//RECETA


@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RecipesComponent,
    RecipeComponent,
    RecipeFormComponent,
    UserMyAccountComponent,
    UserFavouritesComponent,
    UserMenuComponent,
    UserMyRecipesComponent,
    UserPreferencesComponent,
    UserAllRecipesComponent,
    UserPublicProfileComponent
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
  providers: [UsersService, RecipesService, LoginService],
  bootstrap: [AppComponent]
})

export class AppModule { }
