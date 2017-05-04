import { Component } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

import { Restaurant } from './restaurant.model';
import { RestaurantsService } from './restaurants.service';

@Component({
  templateUrl: './user.allRestaurants.component.html'
})

export class UserAllRestaurantsComponent {
    
    user: User;
    restaurants: Restaurant[];

    constructor (private loginService: LoginService, private usersService: UsersService, 
                 private restaurantsService: RestaurantsService) {
        
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                console.log(user);
            },
            error => console.error(error)
        );
        
        this.restaurantsService.getRestaurants().subscribe(
            restaurants => {
                this.restaurants = restaurants.content;
                console.log(this.restaurants);
            },
            error => console.error(error)
        );
    }
}