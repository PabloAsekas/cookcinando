import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { UsersService } from './users.service';

import { User, UserBasic, UserEnterprise, UserAdmin } from './user.model';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent {

    user: User;
    typeUser: string;
    users: User[];
    
    constructor(private loginService: LoginService, private usersService: UsersService, private router: Router) {
        this.usersService.getUsers().subscribe(
            users => { 
                this.users = users;
                console.log(this.users);
             },
            error => console.error(error)
        )
        //this.user={nick:"",email:"",passwordHash:"",roles:[]};
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
            
        if(password === repeatPassword) {
                
            let roles: string[] = [];
            
            if(this.typeUser === 'individual') {
                roles = ['ROLE_BASIC'];
                this.user = { name: '', surname: '', description: '', image: '',
                                nick: nick, email: email, passwordHash: password, 
                                roles: roles, myRecipes: [], favRecipes: [],
                                favRestaurants: [], favEvents: [] }
            }
            else if(this.typeUser === 'empresa') {
                roles = ['ROLE_BASIC', 'ROLE_ENTERPRISE'];
                this.user = { name: '', surname: '', description: '', image: '',
                                nick: nick, email: email, passwordHash: password, 
                                roles: roles, myRecipes: [], favRecipes: [],
                                favRestaurants: [], favEvents: [] }
            }

            else {
                alert('Debe elegir el tipo de usuario');
                return;
            }

            this.usersService.newUser(this.user).subscribe(
                response => {
                    alert('Enhorabuena ya puedes iniciar sesion con tu email y contraseña');
                    
                }, 
                error => console.error('Error al registrar al usuario', error)
            );
        }
        else alert('Las contraseñas NO coinciden');
    }
  /*

  constructor() {}

  logIn() {

  }

  */
 }