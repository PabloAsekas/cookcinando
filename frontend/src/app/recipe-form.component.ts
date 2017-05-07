import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Select2OptionData } from "ng2-select2/ng2-select2";

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
    evento: any;
    
    // Elementos para Select2
    options: Select2Options;
    dataIngredients: string[];
    dataTypesFood: string[];
    ingredients: string[];
    typesFood: string[];
    
    constructor (private router: Router, private loginService: LoginService, private usersService: UsersService, private recipesService: RecipesService, activatedRoute: ActivatedRoute) {
        
        this.options = {
            multiple: true,
            tags: true
        }
        
        let id = activatedRoute.snapshot.params['id'];
        if (id){
            this.editar=true;
            this.recipesService.getRecipe(id).subscribe(
                recipe => {
                    this.recipe = recipe;
                    this.ingredients = this.recipe.ingredients;
                    this.typesFood = this.recipe.typesFood;
                    this.dataIngredients = this.ingredients;
                    this.dataTypesFood = this.typesFood;
                },
                error => console.error(error)
            );
        } else {
            this.dataIngredients = ['Tomate', 'Queso', 'Pan', 'Jamon', 'Chocolate'];
            this.dataTypesFood = ['Meritense', 'QueNoEmeritense', 'OjoCuidao','Andaluza', 'Extremeña', 'Madrileña', 'Valenciana'];
            this.guardar=true;
            this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                this.recipe = { title: "", description: "", thumbnail: "", preparation: "", ingredients: [], typesFood: [], author: this.user};
            },
            error => console.error(error)
        );
        }
    }

    // Metodos para Select2
    changedIngredients(dataIngredients) {

        this.ingredients = dataIngredients.value;
    }

    changedTypesFood(dataTypesFood) {
        this.typesFood = dataTypesFood.value;
    }

    
    /* Mira que sois cazurros de crearos un metodo que ya esta 
     * implementado por JS, que es el toString() -.-"
     * */

    /*rellenar(){
        for (let typeFood of this.recipe.typesFood) {
            this.typesFoodString = this.typesFoodString + typeFood + ",";
        }
        for (let ingredient of this.recipe.ingredients) {
            this.ingredientsString = this.ingredientsString + ingredient + ",";
        }
    }*/

    nuevaReceta(){
        //this.leer();
        this.recipe.ingredients = this.ingredients;
        this.recipe.typesFood = this.typesFood;
        this.recipesService.newRecipe(this.recipe).subscribe(
            recipe =>{
                if(this.evento!=null){
                    this.changeImage(this.evento, recipe.id);
                }
                else{
                    this.router.navigate(['/recetas/', recipe.id]);
                }
            },
            error => console.error('Error creando una nueva receta: ' + error)
        );
    }

    editarReceta(){
        this.recipe.ingredients = this.ingredients;
        this.recipe.typesFood = this.typesFood;
        this.recipesService.updateRecipe(this.recipe).subscribe(
            recipe =>{
                if(this.evento!=null){
                    this.changeImage(this.evento, recipe.id);
                }
                else{
                    this.router.navigate(['/recetas/', recipe.id]);
                }
            },
            error => console.error('Error editando una receta: ' + error)
        );
       
    }
    
    saveEvent(event:any) {
        this.evento = event;
    }
    
    changeImage(event:any, recipeid: number){
        let files = event.target.files;
        console.log(files);
        this.recipesService.changeImage(recipeid,files).subscribe(
            recipe => {
                this.router.navigate(['/recetas/', recipe.id]);
            },
            error =>  console.error('Error al subir la imagen')
        );
    }
    
    ngOnInit() {
        
    }
}
