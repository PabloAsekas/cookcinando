import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './user.myaccount.component.html'
})

export class UserMyAccountComponent {
    
    user: User;
    myAccount = true;
    
    constructor (private loginService: LoginService, private usersService: UsersService) {
    }
    
    update(name: string, surname: string, description: string) {
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                this.user.name = 'paca';
                this.usersService.updateUser(this.user);
            },
            error => console.error(error)
        );
        
        //this.user.name = name;
        //this.user.surname = surname;
        //this.user.description = description;
        
        //this.usersService.updateUser(this.user);
        
        /*this.loginService.logIn(user, pass).subscribe(
            u => console.log(u),
            error => alert('Invalid user or password')
        );*/
        
        
    }
}
