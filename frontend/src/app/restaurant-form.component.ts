import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

import { Restaurant } from './restaurant.model';
import { RestaurantsService } from './restaurants.service';


@Component({
    templateUrl: './restaurant-form.component.html'
})

export class RestaurantFormComponent {

    restaurant: Restaurant;
    newRestaurant: boolean;
    tiposComidas: String = "";
    //user: User;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, 
                private restaurantsService: RestaurantsService, private loginService: LoginService,
                private userService: UsersService) {

        let id = activatedRoute.snapshot.params['id'];

        if(id) {
            this.restaurantsService.getRestaurant(id).subscribe(
                restaurant => {
                    this.restaurant = restaurant;
                    this.tiposComidas = this.restaurant.typesFood.toString();
                    this.newRestaurant = false;
                    console.log(this.tiposComidas);
                    console.log(this.restaurant);
                },
                error => console.error(error)
            );
            
        }
        else {
            this.newRestaurant = true;
            this.restaurant = { title: '', description: '', thumbnail: '', body: '', typesFood: [], author: this.loginService.user};
        }  
    }

    cancelar() {
        window.history.back();
    }

    nuevoRestaurante() {

        const typesFood = this.tiposComidas.split(','); // El String de tipos de comida se tranforma en array

        for(let typeFood of typesFood) {
            if(typeFood !== '') {
                this.restaurant.typesFood.push(typeFood);
            }
        }

        this.restaurantsService.newRestaurant(this.restaurant).subscribe(
            restaurant => {
                console.log(this.restaurant);
                this.router.navigate(['/restaurantes/', this.restaurant.id]);
                //this.restaurant = restaurant,
            },
            error => console.error('Error creando un nuevo restaurante: ' + error)
        );   
    }

    editarRestaurante() {
        
        const typesFood = this.tiposComidas.split(',');
    
        for(let typeFood of typesFood) {
            if(typeFood !== '') {
                this.restaurant.typesFood.push(typeFood);
            }
        }

        this.restaurantsService.updateRestaurant(this.restaurant).subscribe(
            restaurant => {
                console.log(this.restaurant);
                this.router.navigate(['/restaurantes/', this.restaurant.id]);
                //this.restaurant = restaurant,
            },
            error => console.error('Error editando el restaurante: ' + error)
        ); 
    }


}
