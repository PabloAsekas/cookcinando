import { Component } from '@angular/core';

import { User, UserBasic, UserEnterprise } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './user.allUsers.component.html'
})

export class UserAllUsersComponent {
    
    users: User[];
    usersBasic: User[] = [];
    usersEnterprise: User[] = [];
    user: User;

    constructor (private usersService: UsersService, private loginService: LoginService) {
        
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                console.log(user);
            },
            error => console.error(error)
        );

        this.usersService.getUsers().subscribe(
            users => {
                this.users = users;
                console.log('Todos los usuarios de la app: ' , this.users);
                this.separarTiposUsuarios();
            },
            error => console.error(error)
        );
    }

    separarTiposUsuarios() {
        
        for(let user of this.users) {
            if(user.roles.length === 1) {
                this.usersBasic.push(user);
            }
            else if(user.roles.length === 2) {
                this.usersEnterprise.push(user);
            }
        }
        console.log('Usuarios basicos: ' , this.usersBasic);
        console.log('Usuarios enterprise: ' , this.usersEnterprise);
    }
 
}