import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BrowserModule, DomSanitizer, SafeUrl } from '@angular/platform-browser'

import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';

import { LoginService } from './login.service';

import { User } from './user.model';
import { UsersService } from './users.service';

@Component({
  templateUrl: './recipe.component.html'
})

export class RecipeComponent /*implements OnInit*/ {
    recipe: Recipe;
    recipes: Recipe[] = [];
    buttonFav: boolean;
    thumbnailSafe: SafeUrl;
    thumbnailA: String = '<div class="thumbnail-receta" style=" background: url(';
    thumbnailB: String = ') no-repeat 50% fixed;background-size: 100%;"></div>';
    
    user: User;
    
    constructor (private router: Router, private recipesService: RecipesService, activatedRoute: ActivatedRoute, private sanitizer: DomSanitizer, private loginService: LoginService, private usersService: UsersService ) {
        let id = activatedRoute.params.subscribe(params => {
            
            this.recipesService.getRecipe(params['id']).subscribe(
                recipe => {this.recipe = recipe;
                           this.makeThumbnailSafe(this.recipe.thumbnail);
                           },
                error => console.error(error)
            );
            this.recipesService.getRecommended().subscribe(
                recipes => this.recipes = recipes,
                error => console.error(error)
            );
            
            if(this.loginService.isLogged) {
                this.usersService.getUser(this.loginService.user.id).subscribe(
                    user => {
                        this.user = user;
                        this.buttonFav = this.isFavourite();
                        console.log(this.buttonFav);
                    },
                    error => console.error(error)
                );
            }
        });
    }
        
    makeThumbnailSafe(url: string) {
        this.thumbnailSafe = (this.sanitizer.bypassSecurityTrustHtml(this.thumbnailA + url + this.thumbnailB));
    }
    
    isFavourite(){
        
        for (let fav of this.user.favRecipes) {
            if(fav.id == this.recipe.id){
                return true;
            }
        }
        return false;
    }
    
    addFavourite() {
        this.user.favRecipes.push(this.recipe);
        this.usersService.updateUser(this.user).subscribe (
            user => {
                this.buttonFav = true;
            },
            error => console.error('Error al actualizar al añadir una receta a favoritos: ' + error)
        );
    }
    
    deleteFavourite() {
        var position = -1;
        for (let fav of this.user.favRecipes) {
            if(fav.id == this.recipe.id){
                position = this.user.favRecipes.indexOf(fav);
            }
        }
        console.log(this.user.favRecipes[position]);
        if (position > -1) {
           this.user.favRecipes.splice(position, 1);
        }
        this.usersService.updateUser(this.user).subscribe (
            user => {
                this.buttonFav = false;
            },
            error => console.error('Error al actualizar al eliminar una receta de favoritos: ' + error)
        );
    }
    
    delete() {
        const okResponse = window.confirm('¿Seguro que quieres eliminar la receta?');
        if (okResponse) {
            this.recipesService.deleteRecipe(this.recipe).subscribe(
                _ => this.router.navigate(['/recetas']),
                error => console.error('Error al eliminar la receta: ' + error)
            );
        }
    }
    
    edit() {
        this.router.navigate(['/privado/recetas/editar', this.recipe.id]);
    }
    
/*  ngOnInit() {
    }*/
}