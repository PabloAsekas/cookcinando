import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { User, UserBasic, UserEnterprise, UserAdmin } from './user.model';

const BASE_URL = 'https://localhost:8443/api/users/'; /*MIRAR GITHUB DE OTROS COMPAÑEROS*/

@Injectable()
export class UsersService {

    constructor(private http : Http) { }

    getUsers() {
        return this.http.get(BASE_URL)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getUser(id : number | string) {
        return this.http.get(BASE_URL + id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    newUser(user : User) {
        return this.http.post(BASE_URL, user)
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'));
    }

    updateUser(user : User) {
        return this.http.put(BASE_URL + user.id, user)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    deleteUser(user : User) {
        return this.http.delete(BASE_URL + user.id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    private handleError(error : any) {
        console.error(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text());
    }
}
