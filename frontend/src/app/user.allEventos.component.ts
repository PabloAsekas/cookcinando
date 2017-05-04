import { Component } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

import { Evento } from './evento.model';
import { EventosService } from './eventos.service';

@Component({
  templateUrl: './user.allEventos.component.html'
})

export class UserAllEventosComponent {

    user: User;
    eventos: Evento[];
    //recipess = true;

    constructor (private loginService: LoginService, private usersService: UsersService,
                 private eventosService: EventosService) {

        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
                console.log(user);
            },
            error => console.error(error)
        );

        this.eventosService.getEventos().subscribe(
            eventos => {
                this.eventos = eventos.content;
                console.log(this.eventos);
            },
            error => console.error(error)
        );
    }
}
