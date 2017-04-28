import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { Recipe } from './recipe.model';

const BASE_URL = 'http://127.0.0.1:4200/api/recipes/';

@Injectable()
export class RecipesService {
    constructor(private http: Http) { }

    getRecipes() {
        return this.http.get(BASE_URL)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    
    
    getRecipesPag(page?: String) {
        return this.http.get(BASE_URL + page).map(
            response => response.json().content
        ).catch(error => this.handleError(error));
    }
    
    getAmountRecipes(){
        return this.http.get(BASE_URL + '').map(
            response => response.json().totalElements
        ).catch(error => Observable.throw('Error: resource not found'));
    }

    getRecipe(id: number | string) {
        return this.http.get(BASE_URL + id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    newRecipe(recipe: Recipe) {
        return this.http.post(BASE_URL, recipe)
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'));
    }

    updateRecipe(recipe: Recipe) {
        return this.http.put(BASE_URL + recipe.id, recipe)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    deleteRecipe(recipe: Recipe) {
        return this.http.delete(BASE_URL + recipe.id)
            .map(response => undefined)
            .catch(error => this.handleError(error));
    }

    getRecommended() {
        return this.http.get(BASE_URL + 'recommended')
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    
    getByTitle(food: string) {
        return this.http.get(BASE_URL + 'by-title/?title=' + food)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    
    getByIngredient(food: string) {
        return this.http.get(BASE_URL + 'by-ingredient/?ingredient=' + food)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getByTypeFood(food: string) {
        return this.http.get(BASE_URL + 'by-typeFood/?typeFood=' + food)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    
    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }
}
