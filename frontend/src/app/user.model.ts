import { Recipe } from './recipe.model';

export interface User {
    id?: number;
    name: string;
    surname: string;
    description: string;
    image: string;
    nick: string;
    email: string;
    passwordHash: string;
    roles: string[];
    myRecipes: Recipe[];
    favRecipes: Recipe[];
    // favRestaurants: Restaurant[];
    // favEvents: Evento[];
}

export interface UserBasic extends User {
    id?: number;
 }

export interface UserEnterprise extends User {
    id?: number;
    // myRestaurants: Restaurant[];
    // myEvents: Evento[];
}

export interface UserAdmin extends User {
    id?: number;
    // myRestaurants: Restaurant[];
    // myEvents: Evento[];
    users: User[];
}
