import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BrowserModule, DomSanitizer, SafeUrl } from '@angular/platform-browser';

import { Restaurant } from './restaurant.model';
import { RestaurantsService } from './restaurants.service';

import { LoginService } from './login.service';

import { User } from './user.model';
import { UsersService } from './users.service';

@Component({
    templateUrl: './restaurant.component.html'
})

export class RestaurantComponent {

    restaurant: Restaurant;
    restaurants: Restaurant[] = [];
    buttonFav: boolean;
    thumbnailSafe: SafeUrl;
    thumbnailA: String = '<div class="thumbnail-restaurante" style=" background: url(';
    thumbnailB: String = ') no-repeat 50% fixed;background-size: 100%;"></div>';

    user: User;
    
    constructor (private router: Router, private restaurantsService: RestaurantsService, activatedRoute: ActivatedRoute, private sanitizer: DomSanitizer, private loginService: LoginService, private usersService: UsersService ) {
        let id = activatedRoute.params.subscribe(params => {
            
            this.restaurantsService.getRestaurant(params['id']).subscribe(
                restaurant => {this.restaurant = restaurant;
                           this.makeThumbnailSafe(this.restaurant.thumbnail);
                           },
                error => console.error(error)
            );
            this.restaurantsService.getRecommended().subscribe(
                restaurants => this.restaurants = restaurants,
                error => console.error(error)
            );
            
            if(this.loginService.isLogged) {
                this.usersService.getUser(this.loginService.user.id).subscribe(
                    user => {
                        this.user = user;
                        this.buttonFav = this.isFavourite();
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
        
        for (let fav of this.user.favRestaurants) {
            if(fav.id == this.restaurant.id){
                return true;
            }
        }
        return false;
    }

    addRestaurantFavourite() {
        this.user.favRestaurants.push(this.restaurant);
        console.log(this.user);
        console.log(this.user.favRestaurants);
        this.usersService.updateUser(this.user).subscribe (
            user => {
                this.buttonFav = true;
            },
            error => console.error('Error al actualizar al añadir una receta a favoritos: ' + error)
        );
    }

    deleteRestaurantFavourite() {
        var position = -1;
        for (let fav of this.user.favRestaurants) {
            if(fav.id == this.restaurant.id){
                position = this.user.favRestaurants.indexOf(fav);
            }
        }
        console.log(this.user.favRestaurants[position]);
        if (position > -1) {
           this.user.favRestaurants.splice(position, 1);
        }
        this.usersService.updateUser(this.user).subscribe (
            user => {
                this.buttonFav = false;
            },
            error => console.error('Error al actualizar al eliminar una receta de favoritos: ' + error)
        );
     }

    delete() {
        
        const okResponse = window.confirm('¿Seguro que quieres eliminar el restaurante?');

        if(okResponse) {
            this.restaurantsService.deleteRestaurant(this.restaurant).subscribe(
                _ => this.router.navigate(['/restaurantes']),
                error => console.error('Error al eliminar el restaurante: ' + error)
            );
        }
    }

    edit() {
        this.router.navigate(['/privado/restaurantes/editar', this.restaurant.id]);
    }
}