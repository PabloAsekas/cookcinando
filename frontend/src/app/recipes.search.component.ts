import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser'

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  templateUrl: './recipes.search.component.html'
})

export class RecipesSearchComponent {

    recipes: Recipe[];
    search: string;
    
    constructor (private router: Router, activatedRoute: ActivatedRoute, private recipesService: RecipesService) {
        let search = activatedRoute.params.subscribe(
            params => {
                this.recipesService.getByTitle(params['search']).subscribe(
                    recipes => {
                        this.search = params['search'];
                        this.recipes = recipes;
                    },
                    error => console.error(error)
                );
            }
        );
    }
}
