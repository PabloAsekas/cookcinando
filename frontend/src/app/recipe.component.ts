import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  // selector: 'app-root',
  templateUrl: './recipe.component.html'
  // styleUrls: ['../styles/app.component.css']
})

export class RecipeComponent /*implements OnInit*/ {
    recipe: Recipe;
    recipes: Recipe[] = [];
    constructor (private recipesService: RecipesService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        this.recipesService.getRecipe(id).subscribe(
            recipe => this.recipe = recipe,
            error => console.error(error)
        );
        this.recipesService.getRecommended().subscribe(
            recipes => this.recipes = recipes,
            error => console.error(error)
        );
    }

/*  ngOnInit() {
        this.recipesService.getRecipes().subscribe(
            recipes => {
                this.recipes = recipes.content;
                console.log(this.recipes);
            },
            error => console.log(error)
        );
    }*/
}