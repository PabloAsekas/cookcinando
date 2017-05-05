import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { User } from './user.model';
import { UsersService } from './users.service';

import { LoginService } from './login.service';

@Component({
  templateUrl: './user.myaccount.component.html'
})

export class UserMyAccountComponent {
    
    user: User;
    evento: any;
    
    constructor (private loginService: LoginService, private usersService: UsersService, private router: Router, activatedRoute: ActivatedRoute) {
        this.usersService.getUser(this.loginService.user.id).subscribe(
            user => {
                this.user = user;
            },
            error => console.error(error)
        );
    }
    
    update() {
        this.usersService.updateUser(this.user).subscribe(
            user => {
                this.changeImage(this.evento, user.id);
            },
            error => console.error('Error actualizando el usuario: ' + error)
        );
    }
    saveEvent(event:any) {
        this.evento = event;
    }
    
    changeImage(event:any, userid: number){
        let files = event.target.files;
        this.usersService.changeImage(userid,files).subscribe(
            user => {
                this.router.navigate(['/']);
            },
            error =>  console.error('Error al subir la imagen')
        );
    }
    
}
