import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { Restaurant } from './restaurant.model';

const BASE_URL = 'http://127.0.0.1:4200/api/restaurants/';

@Injectable()
export class RestaurantsService {

    constructor(private http: Http) { }

    // GET para obtener TODOS los restaurantes
    getRestaurants() {
        return this.http.get(BASE_URL, { withCredentials: true })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    // GET para obtener UN restaurante
    getRestaurant(id: Number | String) {
        return this.http.get(BASE_URL + id, { withCredentials: true })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    // GET para obtener los restaurantes recomendados
    getRecommended() {
        return this.http.get(BASE_URL + 'recommended', { withCredentials: true })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    // GET para obtener los restaurantes por pagina
    getRestaurantsPag(page?: String, ) {
        return this.http.get(BASE_URL + page, { withCredentials: true }) 
            .map(response => response.json().content)
            .catch(error => this.handleError(error));
    }

    // GET para obtener la cantidad de restaurantes
    getAmountRestaurants() {
        return this.http.get(BASE_URL + '', { withCredentials: true })
            .map(response => response.json().totalElements)
            .catch(error => Observable.throw('Error: resource not found'));
    }

    // POST para añadir un restaurante nuevo
    newRestaurant(restaurant: Restaurant) {

        return this.http.post(BASE_URL, restaurant, { withCredentials: true }) 
                .map(response => response.json())
                .catch(error => this.handleError(error));

        /*const body = JSON.stringify(restaurant);
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        if(!restaurant.id) {
            return this.http.post(BASE_URL, body, options) 
                .map(response => response.json())
                .catch(error => this.handleError(error));
                //.catch(error => Observable.throw('Server error'));
        }
        else {
            return this.http.put(BASE_URL, restaurant, options) 
                .map(response => response.json())
                .catch(error => this.handleError(error));
                //.catch(error => Observable.throw('Server error'));
        }*/   
    }

    // PUT para editar un restaurante
    updateRestaurant(restaurant: Restaurant) {
        
        return this.http.put(BASE_URL + restaurant.id, restaurant, { withCredentials: true })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    // DELETE para borrar un restaurante
    deleteRestaurant(restaurant: Restaurant) {

        return this.http.delete(BASE_URL + restaurant.id, { withCredentials: true })
            .map(response => undefined)
            .catch(error => this.handleError(error));
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

}