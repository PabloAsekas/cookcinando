import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './user.preferences.component.html'
})

export class UserPreferencesComponent {
    
    user: User;
    preferences = true;
    
    constructor (private loginService: LoginService, private usersService: UsersService) {
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
            },
            error => console.error(error)
        );
    }
    
    update() {
        this.usersService.updateUser(this.user).subscribe(
            user => {},
            error => console.error('Error actualizando el usuario: ' + error)
        );
    }
}
