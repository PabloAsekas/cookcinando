import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser'

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  templateUrl: './recipes.ingredients.component.html'
})

export class RecipesIngredientsComponent {

    recipes: Recipe[];
    food: string;
    
    constructor (private router: Router, activatedRoute: ActivatedRoute, private recipesService: RecipesService) {
        let ingredients = activatedRoute.params.subscribe(
            params => {
                this.recipesService.getByIngredient(params['food']).subscribe(
                    recipes => {
                        this.food = params['food'];
                        this.recipes = recipes;
                    },
                    error => console.error(error)
                );
            }
        );
    }
}
