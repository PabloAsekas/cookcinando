import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from './login.service';
import { RecipesService } from './recipes.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent {

    searchBox: String;
    ingredients: false;
    typefood: false;

    constructor(private loginService: LoginService, private router: Router, private recipesService: RecipesService) { }

    logOut() {
        this.loginService.logOut().subscribe(
            response => {
                this.router.navigate(['/']);
            },
            error => console.log('Error when trying to log out: ' + error)
        );
    }

    search() {
        if (this.ingredients) {
            this.router.navigate(['/recetas/ingredientes', this.searchBox]);
        } else if (this.typefood) {
            this.router.navigate(['/recetas/tipo-comida', this.searchBox]);
        } else {
            this.router.navigate(['/recetas/buscador', this.searchBox]);
        }
    }
}
