import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  templateUrl: './user.allrecipes.component.html'
})

export class UserAllRecipesComponent {
    
    user: User;
    recipes: Recipe[];

    constructor (private loginService: LoginService, private usersService: UsersService, private recipesService: RecipesService) {
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                console.log(user);
            },
            error => console.error(error)
        );
        
        this.recipesService.getRecipes().subscribe(
            recipes => {
                this.recipes = recipes.content;
                console.log(this.recipes);
            },
            error => console.error(error)
        );
    }
}