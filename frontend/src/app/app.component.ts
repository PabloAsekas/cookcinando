import { Component, OnInit } from '@angular/core';

import { LoginService } from './login.service';

import { User } from './user.model';
import { UsersService } from './users.service';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`
})

export class AppComponent implements OnInit {

    user: User;
    users: User[] = [];
    recipes: Recipe[] = [];

  constructor (private usersService: UsersService, private recipesService: RecipesService, private loginService: LoginService) { }

  ngOnInit() {
    /*this.usersService.getUser(1).subscribe(
      user => {
        this.user = user;
        console.log(this.user);
      },
      error => console.log(error)
    );
    this.usersService.getUsers().subscribe(
      users => {
        this.users = users;
        console.log(this.users);
      },
      error => console.log(error)
    );

    this.recipesService.getRecipes().subscribe(
      recipes => {
        this.recipes = recipes;
        console.log(this.recipes);
      },
      error => console.log(error)
    );*/
  }
}
