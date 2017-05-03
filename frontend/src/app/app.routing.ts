import { RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';
import { LoginComponent } from './login.component';
// User
import { UserMyAccountComponent } from './user.myaccount.component';
import { UserFavouritesComponent } from './user.favourites.component';
import { UserMyRecipesComponent } from './user.myrecipes.component';
import { UserMyRestaurantsComponent } from './user.myRestaurants.component';
import { UserPreferencesComponent } from './user.preferences.component';
import { UserAllRecipesComponent } from './user.allrecipes.component';
import { UserAllRestaurantsComponent } from './user.allRestaurants.component';
import { UserAllUsersComponent } from './user.allUsers.component';
import { UserPublicProfileComponent } from './user.publicprofile.component';
// Recipes
import { RecipesComponent } from './recipes.component';
import { RecipeComponent } from './recipe.component';
import { RecipeFormComponent } from './recipe-form.component';
import { RecipesSearchComponent } from './recipes.search.component';
import { RecipesIngredientsComponent } from './recipes.ingredients.component';
import { RecipesTypesFoodComponent } from './recipes.typesfood.component';
// Restaurants
import { RestaurantsComponent } from './restaurants.component';
import { RestaurantComponent } from './restaurant.component';
import { RestaurantFormComponent } from './restaurant-form.component';

const appRoutes = [
    { path: '', component: IndexComponent },
    { path: 'login', component: LoginComponent },
    //Usuario
    { path: 'privado/mi-cuenta', component: UserMyAccountComponent },
    { path: 'privado/favoritos', component: UserFavouritesComponent },
    { path: 'privado/mis-recetas', component: UserMyRecipesComponent },
    { path: 'privado/mis-restaurantes', component: UserMyRestaurantsComponent },
    { path: 'privado/ajustes', component: UserPreferencesComponent },
    { path: 'privado/todas-recetas', component: UserAllRecipesComponent },
    { path: 'privado/todos-restaurantes', component: UserAllRestaurantsComponent },
    { path: 'privado/todos-usuarios', component: UserAllUsersComponent },
    { path: 'usuarios/:id', component: UserPublicProfileComponent },
    // Recetas
    { path: 'recetas', component: RecipesComponent },
    { path: 'recetas/:id', component: RecipeComponent },
    { path: 'recetas/buscador/:search', component: RecipesSearchComponent},
    { path: 'recetas/ingredientes/:food', component: RecipesIngredientsComponent},
    { path: 'recetas/tipo-comida/:food', component: RecipesTypesFoodComponent},
    { path: 'privado/recetas/nuevo', component: RecipeFormComponent },
    { path: 'privado/recetas/editar/:id', component: RecipeFormComponent },
    // Restaurantes
    { path: 'restaurantes', component: RestaurantsComponent },
    { path: 'restaurantes/:id', component: RestaurantComponent },
    { path: 'privado/restaurantes/nuevo', component: RestaurantFormComponent },
    { path: 'privado/restaurantes/editar/:id', component: RestaurantFormComponent },
    //{ path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);