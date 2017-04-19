import { RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { LoginComponent } from './login.component';
import { RecipesComponent } from './recipes.component';
import { RecipeComponent } from './recipe.component';

const appRoutes = [
    { path: 'index', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    { path: 'recipes', component: RecipesComponent },
    { path: 'recipes/:id', component: RecipeComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);