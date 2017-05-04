import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './user.myaccount.component.html'
})

export class UserMyAccountComponent {
    
    user: User;
    
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
        
        //this.usersService.updateUser(this.user);
        
        /*this.loginService.logIn(user, pass).subscribe(
            u => console.log(u),
            error => alert('Invalid user or password')
        );*/
        
        
    }
}
