import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`
  // styleUrls: ['../styles/app.component.css']
})

export class AppComponent implements OnInit {

    users: User[] = [];
    recipes: Recipe[] = [];

  constructor (private usersService: UsersService, private recipesService: RecipesService) { }

  ngOnInit() {
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
    );
  }
 }
