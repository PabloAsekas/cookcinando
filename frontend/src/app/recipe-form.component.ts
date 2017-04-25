import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  // selector: 'app-root',
  templateUrl: './recipe-form.component.html'
  // styleUrls: ['../styles/app.component.css']
})

export class RecipeFormComponent implements OnInit {
    editar: Boolean;
    guardar: Boolean;
    user: User;
    recipe: Recipe;
    typesFoodString: String = "";
    ingredientsString: String = "";
    Mrecipes = true;
    constructor (private loginService: LoginService, private usersService: UsersService, private recipesService: RecipesService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        if (id){
            this.editar=true;
            this.recipesService.getRecipe(id).subscribe(
                recipe => {this.recipe = recipe
                           this.rellenar()},
                error => console.error(error)
            );
        } else {
            this.guardar=true;
            this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                this.recipe = { title: "", description: "", thumbnail: "", preparation: "", ingredients: [], typesFood: [], author: this.user};
            },
            error => console.error(error)
        );
            //this.recipe = { title: "", description: "", thumbnail: "", preparation: "", ingredients: [], typesFood: [], author: this.user}
        }
    }
    
    rellenar(){
        for (let typeFood of this.recipe.typesFood) {
            this.typesFoodString = this.typesFoodString + typeFood + ",";
        }
        for (let ingredient of this.recipe.ingredients) {
            this.ingredientsString = this.ingredientsString + ingredient + ",";
        }
    }
    
    leer(){
        this.recipe.ingredients=[];
        for (let ingredient of this.ingredientsString.split(",")) {
            if(ingredient!=""){
                this.recipe.ingredients.push(ingredient);
            }
        }
        this.recipe.typesFood=[];
        for (let typeFood of this.typesFoodString.split(",")) {
            if(typeFood!=""){
                this.recipe.typesFood.push(typeFood);
            }
        }
    }
    nuevaReceta(){
        this.leer();
        this.recipesService.newRecipe(this.recipe).subscribe(
            recipe =>{},
            error => console.error('Error creating new book: ' + error)
        );
    }
    editarReceta(){
        this.leer();
        this.recipesService.updateRecipe(this.recipe).subscribe(
            recipe =>{},
            error => console.error('Error creating new book: ' + error)
        );
       
    }
    ngOnInit() {
        
    }
}
