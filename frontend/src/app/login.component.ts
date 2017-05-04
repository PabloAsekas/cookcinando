import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { UsersService } from './users.service';

import { User, UserBasic, UserEnterprise, UserAdmin } from './user.model';

@Component({
    selector: 'login',
    templateUrl: './login.component.html'
})

export class LoginComponent {

    user: User;
    tUser: string;
    
    constructor(private loginService: LoginService, private usersService: UsersService, private router: Router) {
        this.user={nick:"",email:"",passwordHash:"",roles:[]};
    }

    logIn(event: any, user: string, pass: string) {

        event.preventDefault();

        this.loginService.logIn(user, pass).subscribe(
            u => {console.log(u);
                  this.router.navigate(['/privado/mi-cuenta']);
                 },
            error => alert('Invalid user or password')
        );
    }

    logOut() {
        this.loginService.logOut().subscribe(
            response => { },
            error => console.log('Error when trying to log out: ' + error)
        );
    }

//    registry(nick: string, email: string, password: string, repeatPassword: String, typeUser: String) {
//        if(password === repeatPassword) {
//            if(typeUser === 'Usuario Individual') {
//                this.userb.email = email;
//                this.userb.nick = nick;
//                this.userb.passwordHash = password;
//            }
//            else if(typeUser === 'Usuario Empresa') {
//            }
//        }
//        else console.log('Las contraseñas no coinciden');
//    }
    registry(nick: string, email: string, password: string, repeatPassword: string) {
        console.log(nick);
        console.log(email);
        console.log(password);
        console.log(repeatPassword);
        console.log(this.tUser);
        if(password === repeatPassword) {
            if(this.tUser === 'individual') {
                this.user.name = "";
                this.user.surname = '';
                this.user.description = '';
                this.user.image = '';
                this.user.nick = nick;
                this.user.email = email;
                this.user.passwordHash = password;
                this.user.roles = ['ROLE_BASIC'];
                this.usersService.newUser(this.user).subscribe(
                    response => {
                        console.log("Chavales todo perfecto");
                        window.alert('USUARIO CREADO (Ahora solo te queda iniciar sesión CAMPEÓN)');

                    },
                    error => console.log('Error al crear un usuario: ' + error)
                );
            }
            else if(this.tUser === 'empresa') {
                this.user.name = "";
                this.user.surname = '';
                this.user.description = '';
                this.user.image = '';
                this.user.nick = nick;
                this.user.email = email;
                this.user.passwordHash = password;
                this.user.roles = ['ROLE_BASIC', 'ROLE_ENTERPRISE'];
                this.usersService.newUser(this.user).subscribe(
                    response => {
                        console.log("Chavales todo perfecto");
                        window.alert('USUARIO CREADO (Ahora solo te queda iniciar sesión CAMPEÓN)');
                    },
                    error => console.log('Error al crear un usuario: ' + error)
                );
            }
        }
        else console.log('Las contraseñas no coinciden');
    }
  /*

  constructor() {}

  logIn() {

  }

  */
 }