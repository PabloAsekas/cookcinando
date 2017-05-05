import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Restaurant } from './restaurant.model';
import { RestaurantsService } from './restaurants.service';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './restaurant-form.component.html'
})

export class RestaurantFormComponent {
    editar: Boolean;
    guardar: Boolean;
    user: User;
    restaurant: Restaurant;
    typesFoodString: String = "";
    evento: any;
    
    constructor (private router: Router, private loginService: LoginService, private usersService: UsersService, private restaurantsService: RestaurantsService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        if (id){
            this.editar=true;
            this.restaurantsService.getRestaurant(id).subscribe(
                restaurant => {
                    this.restaurant = restaurant;
                    this.rellenar();
                },
                error => console.error(error)
            );
        } else {
            this.guardar=true;
            this.usersService.getUser(this.loginService.user.id).subscribe(
                user => {
                    this.user = user;
                    this.restaurant = { title: "", description: "", thumbnail: "", body: "", typesFood: [], author: this.user};
                },
                error => console.error(error)
            );
        }
    }
    
    rellenar(){
        for (let typeFood of this.restaurant.typesFood) {
            this.typesFoodString = this.typesFoodString + typeFood + ",";
        }
    }
    
    leer(){
        this.restaurant.typesFood=[];
        for (let typeFood of this.typesFoodString.split(",")) {
            if(typeFood!=""){
                this.restaurant.typesFood.push(typeFood);
            }
        }
    }
    nuevoRestaurante(){
        this.leer();
        this.restaurantsService.newRestaurant(this.restaurant).subscribe(
            restaurant =>{
                if(this.evento!=null){
                    this.changeImage(this.evento, restaurant.id);
                }
                else{
                    this.router.navigate(['/restaurantes/', restaurant.id]);
                }
            },
            error => console.error('Error creando una nueva receta: ' + error)
        );
    }
    editarRestaurante(){
        this.leer();
        this.restaurantsService.updateRestaurant(this.restaurant).subscribe(
            restaurant =>{
                if(this.evento!=null){
                    this.changeImage(this.evento, restaurant.id);
                }
                else{
                    this.router.navigate(['/restaurantes/', restaurant.id]);
                }
            },
            error => console.error('Error editando una receta: ' + error)
        );
       
    }
    
    saveEvent(event:any) {
        this.evento = event;
    }
    
    changeImage(event:any, restaurantid: number){
        let files = event.target.files;
        this.restaurantsService.changeImage(restaurantid,files).subscribe(
            restaurant => {
                this.router.navigate(['/restaurantes/', restaurantid]);
            },
            error =>  console.error('Error al subir la imagen')
        );
    }
}
