import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {BrowserModule, DomSanitizer, SafeUrl} from '@angular/platform-browser'

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
    thumbnailSafe: SafeUrl;
    thumbnailA: String = '<div class="thumbnail-receta" style=" background: url(';
    thumbnailB: String = ') no-repeat 50% fixed;background-size: 100%;"></div>';
    constructor (private recipesService: RecipesService, activatedRoute: ActivatedRoute, private sanitizer: DomSanitizer ) {
        let id = activatedRoute.params.subscribe(params => {
            
        this.recipesService.getRecipe(params['id']).subscribe(
            recipe => {this.recipe = recipe
                      this.makeThumbnailSafe(this.recipe.thumbnail) },
            error => console.error(error)
        );
        this.recipesService.getRecommended().subscribe(
            recipes => this.recipes = recipes,
            error => console.error(error)
        );    
            
        });
        
    }
        
    makeThumbnailSafe(url: string) {
        this.thumbnailSafe = (this.sanitizer.bypassSecurityTrustHtml(this.thumbnailA + url + this.thumbnailB));
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
//<div class="thumbnail-receta" style=" background: url(assets/img/empanadas-burguer-con-queso.jpg) no-repeat 50% fixed;background-size: 100%;"></div>