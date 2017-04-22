import { Component } from '@angular/core';
import { LoginService } from './login.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent { 

    constructor(private loginService: LoginService) { }

    logOut() {
        this.loginService.logOut().subscribe(
            response => { },
            error => console.log('Error when trying to log out: ' + error)
        );
    }
}
