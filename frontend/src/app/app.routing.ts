import { RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';

import { RecipeComponent } from './recipe.component';

const appRoutes = [
    { path: 'index', component: IndexComponent },
    { path: 'recipes', component: RecipeComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
]

export const routing = RouterModule.forRoot(appRoutes);