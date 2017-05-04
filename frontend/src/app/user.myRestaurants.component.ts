import { Component } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './user.myRestaurants.component.html'
})

export class UserMyRestaurantsComponent {

    user: User;

    constructor (private loginService: LoginService, private usersService: UsersService) {

        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                console.log(user);
            },
            error => console.error(error)
        );
    }
}
