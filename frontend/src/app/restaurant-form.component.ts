import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Select2OptionData } from "ng2-select2/ng2-select2";

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
    evento: any;

    // Elementos para Select2
    options: Select2Options;
    data: string[];
    typesFood: string[];
    
    constructor (private router: Router, private loginService: LoginService, private usersService: UsersService, private restaurantsService: RestaurantsService, activatedRoute: ActivatedRoute) {
        
        this.options = {
            multiple: true,
            tags: true
        }
        
        let id = activatedRoute.snapshot.params['id'];
        
        if (id){
            this.editar = true;
            this.restaurantsService.getRestaurant(id).subscribe(
                restaurant => {
                    this.restaurant = restaurant;
                    this.typesFood = this.restaurant.typesFood;
                    this.data = this.typesFood;
                },
                error => console.error(error)
            );

        } else {
            this.data = ['Meritense', 'QueNoEmeritense', 'OjoCuidao','Andaluza', 'Extremeña', 'Madrileña', 'Valenciana', 'Gallega'];
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

    // Metodo para Select2
    changed(data) {
        this.typesFood = data.value;
    }
    
    nuevoRestaurante(){
        this.restaurant.typesFood = this.typesFood;
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
        this.restaurant.typesFood = this.typesFood;
        this.restaurantsService.updateRestaurant(this.restaurant).subscribe(
            restaurant =>{
                if(this.evento!=null){
                    this.changeImage(this.evento, restaurant.id);
                }
                else{
                    this.router.navigate(['/restaurantes/', restaurant.id]);
                }
            },
            error => console.error('Error editando un restaurante: ' + error)
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
