import { Component, Input } from '@angular/core';
import { LoginService } from './login.service';

@Component({
    selector: 'app-user-menu',
    templateUrl: './user.menu.component.html'
})

export class UserMenuComponent { 

    @Input()
     private myAccount = false;
    @Input()
     private favourites = false;
    @Input()
     private recipes = false;
    @Input()
     private restaurants = false;
    @Input()
     private events = false;
    @Input()
     private users = false;
    @Input()
     private preferences = false;
    
    private isCollapsedRecipes = true;
    private isCollapsedRestaurants = true;
    private isCollapsedEvents = true;
     
    constructor(private loginService: LoginService) { }
}
