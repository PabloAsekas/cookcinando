import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RestaurantsService } from './restaurants.service';
import { Restaurant } from'./restaurant.model';

@Component({
    templateUrl: './restaurants.typesfood.component.html'
})

export class RestaurantsTypesFoodComponent {
    
    restaurants: Restaurant[];
    food: string;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, 
                private restaurantsService: RestaurantsService,) {

        let typeFood = activatedRoute.params.subscribe(
            params => {
                this.restaurantsService.getByTypeFood(params['food']).subscribe(
                    restaurants => {
                        this.food = params['food'];
                        this.restaurants = restaurants;
                    },
                    error => console.error(error)
                );
            }
        );
    }



}