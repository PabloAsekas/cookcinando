import { Component, OnInit } from '@angular/core';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  templateUrl: './recipes.component.html'
})

export class RecipesComponent implements OnInit {

    recipes: Recipe[] = [];
    private actualPage = 0;
    private nRecipes = 0;
    private loadMore = false;
    
    
    constructor (private recipesService: RecipesService) { }

    ngOnInit() {
        this.recipesService.getRecipes().subscribe(
            recipes => {
                this.recipes = recipes.content;
                console.log(this.recipes);
            },
            error => console.log(error)
        );
        this.recipesService.getAmountRecipes().subscribe(
            nRecipes => this.nRecipes = nRecipes,
            error => console.error(error)
        );
    }
    
    loadMoreRecipes(){
       this.actualPage += 1;
       this.recipesService.getRecipesPag('?page='+ this.actualPage +'&size=10').subscribe(
         recipes => {
                let newRecipes = recipes;
                 for(let recipe of newRecipes){
                     this.recipes.push(recipe);
                 }
                 if(this.recipes.length == this.nRecipes){
                     this.loadMore = true;
                 }
         },
         error => console.log(error)
       );

     }
}
