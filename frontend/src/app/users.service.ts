import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { User, UserBasic, UserEnterprise, UserAdmin } from './user.model';

const BASE_URL = 'http://127.0.0.1:4200/api/users/';

@Injectable()
export class UsersService {

    constructor(private http: Http) { }

    getUsers() {
        return this.http.get(BASE_URL, { withCredentials: true })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getUser(id: number | string) {
        return this.http.get(BASE_URL + id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    newUser(user: User) {
        return this.http.post(BASE_URL + "/registry", user)
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'));
    }

    updateUser(user: User) {
        /*const body = JSON.stringify(user);
        const headers = new Headers({
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });*/
        return this.http.put(BASE_URL + user.id, user)
        //return this.http.put(BASE_URL + user.id, body, options)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    deleteUser(user: User) {
        return this.http.delete(BASE_URL + user.id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }
    
    changeImage(id_user:number,files){
        let formData = new FormData();
        for (let file of files) {
            formData.append('file', file);
        }
        let headers = new Headers({});
        const options = new RequestOptions({ withCredentials: true,headers});
        headers.delete("Content-Type");
        
        
        return this.http.post(BASE_URL + 'uploadImage/' + id_user,formData,options)
            .map(response => response.json())
            .catch(error =>this.handleError(error))
    }
    
}
