import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

@Component({
  // selector: 'app-root',
  templateUrl: './recipe-form.component.html'
  // styleUrls: ['../styles/app.component.css']
})

export class RecipeFormComponent implements OnInit {
    recipe: Recipe;
    typesFoodString: String = "";
    ingredientsString: String = "";
    constructor (private recipesService: RecipesService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        this.recipesService.getRecipe(id).subscribe(
            recipe => {this.recipe = recipe
                       this.rellenar()},
            error => console.error(error)
        );
        //this.rellenar();
    }
    
    rellenar(){
        for (let typeFood of this.recipe.typesFood) {
            this.typesFoodString = this.typesFoodString + typeFood + ",";
        }
        for (let ingredient of this.recipe.ingredients) {
            this.ingredientsString = this.ingredientsString + ingredient + ",";
        }
    }
    
    guardarReceta(){
        this.recipe.title="putaaaaaa";
        this.recipesService.updateRecipe(this.recipe).subscribe(
            recipe =>{},
            error => console.error('Error creating new book: ' + error)
        );
    }
   /* 
    save() {
    this.service.saveBook(this.book).subscribe(
      book => { },
      error => console.error('Error creating new book: ' + error)
    );
    window.history.back();
  }
*/
    ngOnInit() {
        //this.rellenar();
    }
}
