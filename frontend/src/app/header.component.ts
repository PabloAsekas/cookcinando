import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from './login.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent { 

    constructor(private loginService: LoginService, private router: Router) { }

    logOut() {
        this.loginService.logOut().subscribe(
            response => {
                this.router.navigate(['/']);
            },
            error => console.log('Error when trying to log out: ' + error)
        );
    }
}
