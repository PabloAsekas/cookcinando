import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { IndexComponent } from './index.component';
import { LoginComponent } from './login.component';

// Hijos de IndexComponent
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
// Fin Hijos de IndexComponent

// Users
import { UsersService } from './users.service';

// Recipes
import { RecipesService } from './recipes.service';
import { RecipesComponent } from './recipes.component'; // TODAS LAS RECETAS
import { RecipeComponent } from './recipe.component';   // UNA RECETA


@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RecipesComponent,
    RecipeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    routing
  ],
  providers: [UsersService, RecipesService],
  bootstrap: [AppComponent]
})

export class AppModule { }
