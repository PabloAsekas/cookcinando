import { User } from './user.model';

export interface Restaurant {
    id?: number;
    title: string;
    description: string;
    thumbnail: string;
    body: string;
    typesFood: string[];
    author: User;
}
