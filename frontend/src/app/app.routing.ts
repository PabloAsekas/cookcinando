import { RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { LoginComponent } from './login.component';
import { RecipesComponent } from './recipes.component';
import { RecipeComponent } from './recipe.component';
import { RecipeFormComponent } from './recipe-form.component';
import { UserMyAccountComponent } from './user.myaccount.component';
import { UserFavouritesComponent } from './user.favourites.component';
import { UserMyRecipesComponent } from './user.myrecipes.component';
import { UserPreferencesComponent } from './user.preferences.component';
import { UserAllRecipesComponent } from './user.allrecipes.component';
import { ProfileBasicPublicComponent } from './profile.basic.public.component';


const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    // Recetas
    { path: 'recetas', component: RecipesComponent },
    { path: 'recetas/:id', component: RecipeComponent },
    { path: 'privado/recetas/nuevo', component: RecipeFormComponent },
    { path: 'privado/recetas/editar/:id', component: RecipeFormComponent },
    //Usuario
    { path: 'privado/mi-cuenta', component: UserMyAccountComponent },
    { path: 'privado/favoritos', component: UserFavouritesComponent },
    { path: 'privado/mis-recetas', component: UserMyRecipesComponent },
    { path: 'privado/ajustes', component: UserPreferencesComponent },
    { path: 'privado/todas-recetas', component: UserAllRecipesComponent },
    { path: 'usuarios/:id', component: ProfileBasicPublicComponent }
    //{ path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);