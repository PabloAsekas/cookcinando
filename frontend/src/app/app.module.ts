import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { IndexComponent } from './index.component';

// Hijos de IndexComponent
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
// Fin Hijos de IndexComponent

import { UsersService } from './users.service';

import { RecipesService } from './recipes.service';


@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent
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
