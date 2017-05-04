import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Evento } from './evento.model';
import { EventosService } from './eventos.service';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  // selector: 'app-root',
  templateUrl: './evento-form.component.html'
  // styleUrls: ['../styles/app.component.css']
})

export class EventoFormComponent implements OnInit {
    editar: Boolean;
    guardar: Boolean;
    user: User;
    evento: Evento;
    typesFoodString: String = "";
    Meventos = true;
    constructor (private router: Router, private loginService: LoginService, private usersService: UsersService, private eventosService: EventosService, activatedRoute: ActivatedRoute) {
        let id = activatedRoute.snapshot.params['id'];
        if (id){
            this.editar=true;
            this.eventosService.getEvento(id).subscribe(
                evento => {this.evento = evento
                           this.rellenar()},
                error => console.error(error)
            );
        } else {
            this.guardar=true;
            this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                this.evento = { title: "", description: "", thumbnail: "", body: "", typesFood: [], author: this.user};
            },
            error => console.error(error)
        );
            //this.evento = { title: "", description: "", thumbnail: "", bpdy: "", typesFood: [], author: this.user}
        }
    }

    rellenar(){
        for (let typeFood of this.evento.typesFood) {
            this.typesFoodString = this.typesFoodString + typeFood + ",";
        }
    }

    leer(){
        this.evento.typesFood=[];
        for (let typeFood of this.typesFoodString.split(",")) {
            if(typeFood!=""){
                this.evento.typesFood.push(typeFood);
            }
        }
    }
    nuevoEvento(){
        this.leer();
        this.eventosService.newEvento(this.evento).subscribe(
            evento =>{
                this.router.navigate(['/eventos/', evento.id]);
            },
            error => console.error('Error creando un nuevo evento: ' + error)
        );
    }
    editarEvento(){
        this.leer();
        this.eventosService.updateEvento(this.evento).subscribe(
            evento =>{
                this.router.navigate(['/eventos/', evento.id]);
            },
            error => console.error('Error editando un evento: ' + error)
        );

    }
    ngOnInit() {

    }
}
