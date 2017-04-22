import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

import { User, UserBasic, UserEnterprise, UserAdmin } from './user.model';

@Component({
    selector: 'login',
    templateUrl: './login.component.html'
})

export class LoginComponent {

    constructor(private loginService: LoginService) { }

    logIn(event: any, user: string, pass: string) {

        event.preventDefault();

        this.loginService.logIn(user, pass).subscribe(
            u => console.log(u),
            error => alert('Invalid user or password')
        );
    }

    logOut() {
        this.loginService.logOut().subscribe(
            response => { },
            error => console.log('Error when trying to log out: ' + error)
        );
    }

  /*user: User;

  constructor() {}

  logIn() {

  }

  registry(nick: String, email: String, password: String, repeatPassword: String, typeUser: String) {

    if(password === repeatPassword) {
      
      if(typeUser === 'Usuario Individual') {
        this.user = new UserBasic(nick, email, password);
      }
      else if(typeUser === 'Usuario Empresa') {

      }
    }

    else console.log('Las contraseñas no coinciden');

  }*/
 }