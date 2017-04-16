import { RouterModule } from '@angular/router';

import { IndexComponent } from './index.component';

const appRoutes = [
    { path: 'index', component: IndexComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' },

]

export const routing = RouterModule.forRoot(appRoutes);    