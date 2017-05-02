import { Component, OnInit } from '@angular/core';

import { Restaurant } from './restaurant.model';
import { RestaurantsService } from './restaurants.service';

@Component({
    templateUrl: './restaurants.component.html'
})

export class RestaurantsComponent implements OnInit {

    restaurants: Restaurant[] = [];
    private actualPage = 0;
    private numRestaurants = 0;
    private loadMore = false;

    constructor(private restaurantsService: RestaurantsService) { }

    ngOnInit() {

        this.restaurantsService.getRestaurants().subscribe(
            restaurants => {
                this.restaurants = restaurants.content;
                console.log(this.restaurants);
            },
            error => console.error(error)
        );

        this.restaurantsService.getAmountRestaurants().subscribe(
            numRestaurants => {
                this.numRestaurants = numRestaurants;
                console.log(this.numRestaurants);
            },
            error => console.error(error)
        );
    }

    loadMoreRestaurants() {

        this.actualPage += 1;

        this.restaurantsService.getRestaurantsPag('?page=' + this.actualPage + '&size=9').subscribe(

            restaurants => {
                let moreRestaurants = restaurants;
                
                for(let restaurant of moreRestaurants) {
                    this.restaurants.push(restaurant);
                }

                if(this.restaurants.length === this.numRestaurants) {
                    this.loadMore = true;
                }
            },
            error => console.error(error)
        );
    }
}