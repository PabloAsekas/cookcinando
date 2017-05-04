import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BrowserModule, DomSanitizer, SafeUrl } from '@angular/platform-browser'

import { Evento } from './evento.model';
import { EventosService } from './eventos.service';

import { LoginService } from './login.service';

import { User } from './user.model';
import { UsersService } from './users.service';

@Component({
  templateUrl: './evento.component.html'
})

export class EventoComponent /*implements OnInit*/ {
    evento: Evento;
    eventos: Evento[] = [];
    buttonFav: boolean;
    thumbnailSafe: SafeUrl;
    thumbnailA: String = '<div class="thumbnail-receta" style=" background: url(';
    thumbnailB: String = ') no-repeat 50% fixed;background-size: 100%;"></div>';

    user: User;

    constructor (private router: Router, private eventosService: EventosService, activatedRoute: ActivatedRoute, private sanitizer: DomSanitizer, private loginService: LoginService, private usersService: UsersService ) {
        let id = activatedRoute.params.subscribe(params => {

            this.eventosService.getEvento(params['id']).subscribe(
                evento => {this.evento = evento;
                           this.makeThumbnailSafe(this.evento.thumbnail);
                           },
                error => console.error(error)
            );
            this.eventosService.getRecommended().subscribe(
                eventos => this.eventos = eventos,
                error => console.error(error)
            );

            if(this.loginService.isLogged) {
                this.usersService.getUser(this.loginService.user.id).subscribe(
                    user => {
                        this.user = user;
                        this.buttonFav = this.isFavourite();
                    },
                    error => console.error(error)
                );
            }
        });
    }

    makeThumbnailSafe(url: string) {
        this.thumbnailSafe = (this.sanitizer.bypassSecurityTrustHtml(this.thumbnailA + url + this.thumbnailB));
    }

    isFavourite(){
        for (let fav of this.user.favEvents) {
            if(fav.id == this.evento.id){
                return true;
            }
        }
        return false;
    }

    addFavourite() {
        this.user.favEvents.push(this.evento);
        this.usersService.updateUser(this.user).subscribe (
            user => {
                this.buttonFav = true;
            },
            error => console.error('Error al actualizar al añadir un evento a favoritos: ' + error)
        );
    }

    deleteFavourite() {
        var position = -1;
        for (let fav of this.user.favEvents) {
            if(fav.id == this.evento.id){
                position = this.user.favEvents.indexOf(fav);
            }
        }
        console.log(this.user.favEvents[position]);
        if (position > -1) {
           this.user.favEvents.splice(position, 1);
        }
        this.usersService.updateUser(this.user).subscribe (
            user => {
                this.buttonFav = false;
            },
            error => console.error('Error al actualizar al eliminar un evento de favoritos: ' + error)
        );
    }

    delete() {
        const okResponse = window.confirm('¿Seguro que quieres eliminar el evento?');
        if (okResponse) {
            this.eventosService.deleteEvento(this.evento).subscribe(
                _ => this.router.navigate(['/eventos']),
                error => console.error('Error al eliminar el evento: ' + error)
            );
        }
    }

    edit() {
        this.router.navigate(['/privado/eventos/editar', this.evento.id]);
    }

/*  ngOnInit() {
    }*/
}
