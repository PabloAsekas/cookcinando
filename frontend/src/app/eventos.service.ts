import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { Evento } from './evento.model';

const BASE_URL = 'http://127.0.0.1:4200/api/events/';

@Injectable()
export class EventosService {
    constructor(private http: Http) { }

    getEventos() {
        return this.http.get(BASE_URL)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getEventosPag(page?: String) {
        return this.http.get(BASE_URL + page).map(
            response => response.json().content
        ).catch(error => this.handleError(error));
    }

    getAmountEventos(){
        return this.http.get(BASE_URL + '').map(
            response => response.json().totalElements
        ).catch(error => Observable.throw('Error: resource not found'));
    }

    getEvento(id: number | string) {
        return this.http.get(BASE_URL + id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    newEvento(evento: Evento) {
        return this.http.post(BASE_URL, evento)
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'));
    }

    updateEvento(evento: Evento) {
        return this.http.put(BASE_URL + evento.id, evento)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    deleteEvento(evento: Evento) {
        return this.http.delete(BASE_URL + evento.id)
            .map(response => undefined)
            .catch(error => this.handleError(error));
    }

    getRecommended() {
        return this.http.get(BASE_URL + 'recommended')
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getByTypeFood(food: string) {
        return this.http.get(BASE_URL + 'by-typeFood/?typeFood=' + food)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }
    
    changeImage(id_evento:number,files){
        let formData = new FormData();
        for (let file of files) {
            formData.append('file', file);
        }
        let headers = new Headers({});
        const options = new RequestOptions({ withCredentials: true,headers});
        headers.delete("Content-Type");
        
        
        return this.http.post(BASE_URL + '/uploadImage/' + id_evento,formData,options)
            .map(response => response.json())
            .catch(error =>this.handleError(error))
    }
}
