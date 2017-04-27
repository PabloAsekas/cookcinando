import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser'

import { User } from './user.model';
import { UsersService } from './users.service';

@Component({
  templateUrl: './user.publicprofile.component.html'
})

export class UserPublicProfileComponent {
    
    user: User;
    
    constructor (private router: Router, activatedRoute: ActivatedRoute, private usersService: UsersService ) {
        let id = activatedRoute.params.subscribe(params => {
            
            let id = activatedRoute.snapshot.params['id'];
            this.usersService.getUser(id).subscribe(
                user => {
                    this.user = user;
                    console.log(this.user);    
                },
                error => console.error(error)
            );
            
            /*this.recipesService.getRecipe(params['id']).subscribe(
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
            }*/
        });
    }
}