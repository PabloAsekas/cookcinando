import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { EventosService } from './eventos.service';
import { Evento } from'./evento.model';

@Component({
    templateUrl: './eventos.typesfood.component.html'
})

export class EventosTypesFoodComponent {
    
    eventos: Evento[];
    food: string;

    constructor(private router: Router, private activatedRoute: ActivatedRoute, private eventosService: EventosService) {

        let typeFood = activatedRoute.params.subscribe(
            params => {
                this.eventosService.getByTypeFood(params['food']).subscribe(
                    events => {
                        this.food = params['food'];
                        this.eventos = events;
                        console.log(this.eventos);
                    },
                    error => console.error(error)
                );
            }
        );
    }



}