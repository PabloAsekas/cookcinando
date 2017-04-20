import { Component, OnInit } from '@angular/core';

// import { User } from './user.model';
// import { UsersService } from './users.service';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  // selector: 'app-root',
  templateUrl: './recipes.component.html'
  // styleUrls: ['../styles/app.component.css']
})

export class RecipesComponent implements OnInit {

    recipes: Recipe[] = [];

    constructor (private recipesService: RecipesService) { }

    ngOnInit() {
        this.recipesService.getRecipes().subscribe(
            recipes => {
                this.recipes = recipes.content;
                console.log(this.recipes);
            },
            error => console.log(error)
        );
    }
}
