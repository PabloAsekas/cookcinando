import { Component, OnInit } from '@angular/core';

import { User } from './user.model';
import { UsersService } from './users.service';

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`
  //styleUrls: ['../styles/app.component.css']
})

export class AppComponent implements OnInit {

  users: User[] = [];

  constructor(private usersService : UsersService) { }

  ngOnInit() {
    this.usersService.getUsers().subscribe(
      users => {
        this.users = users;
        console.log(this.users);
      },  
      error => console.log(error)
    );
  }

 }
