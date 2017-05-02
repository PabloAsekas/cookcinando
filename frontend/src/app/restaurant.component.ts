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
    restaurantsRecommended: Restaurant[] = [];
    isRestaurantFav: boolean;
    thumbnailSafe: SafeUrl;
    thumbnailA: String = '<div class="thumbnail-restaurante" style=" background: url(';
    thumbnailB: String = ') no-repeat 50% fixed;background-size: 100%;"></div>';

    user: User;

    constructor(private restaurantsService: RestaurantsService, private router: Router, private activatedRoute: ActivatedRoute,
                private sanitizer: DomSanitizer, private loginService: LoginService, private usersService: UsersService) {

        const id = activatedRoute.snapshot.params['id'];

        this.restaurantsService.getRestaurant(id).subscribe(
            restaurant => {
                this.restaurant = restaurant;
                this.makeThumbnailSafe(this.restaurant.thumbnail);
                console.log(this.restaurant);
            }, 
            error => console.error(error)
        );

        this.restaurantsService.getRecommended().subscribe(
            restaurantsRecommended => {
                this.restaurantsRecommended = restaurantsRecommended;
                console.log(this.restaurantsRecommended);
            }, 
            error => console.error(error)
        );

        console.log(this.loginService.user);

        // NO ES NECESARIO PUESTO QUE EL USUARIO LOGEADO ESTA EN: this.loginService.user
        if(this.loginService.isLogged) {
            this.usersService.getUser(this.loginService.user.id).subscribe(
                user => { 
                    this.user = user;
                    this.isRestaurantFav = this.isFavourite();
                    console.log(this.user);
                },
                error => console.error(error)
            );
        }
    }

    makeThumbnailSafe(url: string) {
        this.thumbnailSafe = (this.sanitizer.bypassSecurityTrustHtml(this.thumbnailA + url + this.thumbnailB));
    }

    isFavourite() {

        for(let restaurantFav of this.user.favRestaurants) {
            if(restaurantFav.id === this.restaurant.id) {                 
               return this.isRestaurantFav = true; // El restaurante esta en la lista de favoritos
            }
        }
        return this.isRestaurantFav = false; // El restaurante NO esta en la lista de favoritos
    }

    addRestaurantFavourite() {
        
        this.user.favRestaurants.push(this.restaurant);
        this.isRestaurantFav = true;
       
        this.usersService.updateUser(this.user).subscribe(
            user => this.user = user,
            error => console.error('Error al añadir el restaurante a favoritos: ' + error)
        );
    }

    deleteRestaurantFavourite() {

        const position = this.user.favRestaurants.indexOf(this.restaurant);
        this.user.favRestaurants.splice(position, 1);

        this.usersService.updateUser(this.user).subscribe(
            user => this.user = user,
            error => console.log('Error al eliminar el restaurante de favoritos: ' + error)
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