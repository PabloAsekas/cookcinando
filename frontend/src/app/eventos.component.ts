import { Component, OnInit } from '@angular/core';

import { Evento } from './evento.model';
import { EventosService } from './eventos.service';

@Component({
  templateUrl: './eventos.component.html'
})

export class EventosComponent implements OnInit {

    eventos: Evento[] = [];
    private actualPage = 0;
    private nEventos = 0;
    private loadMore = false;


    constructor (private eventosService: EventosService) { }

    ngOnInit() {
        this.eventosService.getEventos().subscribe(
            eventos => {
                this.eventos = eventos.content;
                console.log(this.eventos);
            },
            error => console.log(error)
        );
        this.eventosService.getAmountEventos().subscribe(
            nEventos => this.nEventos = nEventos,
            error => console.error(error)
        );
    }

    loadMoreEventos(){
       this.actualPage += 1;
       this.eventosService.getEventosPag('?page='+ this.actualPage +'&size=10').subscribe(
         eventos => {
                let newEventos = eventos;
                 for(let evento of newEventos){
                     this.eventos.push(evento);
                 }
                 if(this.eventos.length == this.nEventos){
                     this.loadMore = true;
                 }
         },
         error => console.log(error)
       );

     }
}
