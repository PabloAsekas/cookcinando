import { User } from './user.model';

export interface Recipe {
    id?: number;
    title: string;
    description: string;
    thumbnail: string;
    preparation: string;
    ingredients: string[];
    typesFood: string[];
    author: User;
}
