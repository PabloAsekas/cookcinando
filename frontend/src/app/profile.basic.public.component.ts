import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { User } from './user.model';
import { UsersService } from './users.service';

@Component({
  templateUrl: './profile.basic.public.component.html'
})

export class ProfileBasicPublicComponent {
    
    user: User;
    isEnterprise: boolean;
    
    constructor (private usersService: UsersService, private router: Router, activatedRoute: ActivatedRoute) {
       
        let id = activatedRoute.params.subscribe(
            params => {
                this.usersService.getUser(params['id']).subscribe(
                    user => {
                        this.user = user;
                        this.isEnterprise = this.user.roles.indexOf('ROLE_ENTERPRISE') !== -1;
                        console.log(this.user.myRecipes);
                    },
                    error => console.error(error)
                );
            },
            error => console.error(error)
       );
    }
    
//    update() {
//        this.usersService.updateUser(this.user).subscribe(
//            user => {},
//            error => console.error('Error actualizando el usuario: ' + error)
//        );
//    }
}
