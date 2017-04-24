import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

import { Recipe } from './recipe.model';
//import { RecipesService } from './recipes.service';

//lo mismo con restaurantes y eventos?

@Component({
  templateUrl: './user.favourites.component.html'
})

export class UserFavouritesComponent {
    
    user: User;
    favourites = true;
    //recipe: Recipe;

    constructor (private loginService: LoginService, private usersService: UsersService) {
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                console.log(user);
            },
            error => console.error(error)
        );
        
        /*for (let recipe of this.loginService.user.favRecipes) {
            console.log(recipe);
        }*/
    }
}