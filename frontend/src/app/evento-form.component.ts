import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Select2OptionData } from "ng2-select2/ng2-select2";

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
    eventoNewImage: any;
    editar: Boolean;
    guardar: Boolean;
    user: User;
    evento: Evento;
    typesFoodString: String = "";
    Meventos = true;
    
    // Elementos para Select2
    options: Select2Options;
    data: string[];
    typesFood: string[];
    
    constructor (private router: Router, private loginService: LoginService, private usersService: UsersService, private eventosService: EventosService, activatedRoute: ActivatedRoute) {
        
        this.options = {
            multiple: true,
            tags: true
        }
        
        let id = activatedRoute.snapshot.params['id'];
        if (id){
            this.editar=true;
            this.eventosService.getEvento(id).subscribe(
                evento => {
                    this.evento = evento;
                    this.typesFood = this.evento.typesFood;
                    this.data = this.typesFood;
                },
                error => console.error(error)
            );
        } else {
            this.data = ['Vinos', 'Cervezas', 'Mojitos', 'Jamon', 'Queso'];
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

    // Metodo para Select2
    changed(data) {
        this.typesFood = data.value;
    }

    nuevoEvento(){
        this.evento.typesFood = this.typesFood;
        this.eventosService.newEvento(this.evento).subscribe(
            evento =>{
                if(this.eventoNewImage!=null){
                    this.changeImage(this.eventoNewImage, evento.id);
                }
                else{
                    this.router.navigate(['/eventos/', evento.id]);
                }
            },
            error => console.error('Error creando un nuevo evento: ' + error)
        );
    }
    
    editarEvento(){
        this.evento.typesFood = this.typesFood;
        this.eventosService.updateEvento(this.evento).subscribe(
            evento =>{
                if(this.eventoNewImage!=null){
                    this.changeImage(this.eventoNewImage, evento.id);
                }
                else{
                    this.router.navigate(['/eventos/', evento.id]);
                }
            },
            error => console.error('Error editando un evento: ' + error)
        );

    }

    saveEvent(event:any) {
        this.eventoNewImage = event;
    }

    changeImage(event:any, eventoid: number){
        let files = event.target.files;
        console.log(files);
        this.eventosService.changeImage(eventoid,files).subscribe(
            evento => {
                this.router.navigate(['/eventos/', evento.id]);
            },
            error =>  console.error('Error al subir la imagen')
        );
    }
    
    ngOnInit() {

    }
}
