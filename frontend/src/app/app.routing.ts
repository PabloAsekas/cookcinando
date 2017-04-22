import { RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { LoginComponent } from './login.component';
import { RecipesComponent } from './recipes.component';
import { RecipeComponent } from './recipe.component';
import { RecipeFormComponent } from './recipe-form.component';
import { UserMyAccountComponent } from './user.myaccount.component';


const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    // Recetas
    { path: 'recetas', component: RecipesComponent },
    { path: 'recetas/:id', component: RecipeComponent },
    { path: 'privado/recetas/editar/:id', component: RecipeFormComponent },
    //Usuario
    { path: 'privado/mi-cuenta', component: UserMyAccountComponent }
    
    //{ path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);