import { User } from './user.model';

export interface Evento {
    id?: number;
    title: string;
    description: string;
    thumbnail: string;
    body: string;
    typesFood: string[];
    author: User;
}
