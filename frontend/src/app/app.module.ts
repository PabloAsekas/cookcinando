import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';

import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { IndexComponent } from './index.component';
import { UsersService } from './users.service';


@NgModule({
  declarations: [
    AppComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    routing
  ],
  providers: [UsersService],
  bootstrap: [AppComponent]
})

export class AppModule { }
