import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RestaurantsService } from './restaurants.service';
import { Restaurant } from'./restaurant.model';

@Component({
    templateUrl: './restaurants-typeFood.component.html'
})

export class RestaurantsTypeFoodComponent {
    
    restaurants: Restaurant[];
    tipoComida:string;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, 
                private restaurantsService: RestaurantsService,) {

        let typeFood = activatedRoute.snapshot.params['tipoComida'];

        this.tipoComida = typeFood; 

        if(typeFood) {
            this.restaurantsService.getRestaurantsByTypeFood(typeFood).subscribe(
                restaurants => {
                    this.restaurants = restaurants;
                    console.log(this.restaurants);
                },
                error => console.error(error)
            );
        }
    }



}