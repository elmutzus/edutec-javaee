import { Injectable } from '@angular/core';
import { IParametro } from './interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
const API_URL = environment.apiUrl;

@Injectable()
export class ParametroService {
    private endpointUrl = API_URL + "/parametros";

    constructor(private http: HttpClient) {

    }
    
    getEntities(): Observable<IParametro[]> {
        return this.http.get(this.endpointUrl)
        .map((response) => {
            return response;
        })
        .catch((error) => {
            return Observable.throw(error.statusText);
        });
    }
    
    saveMany(entities: IParametro[]): Observable<IParametro[]> {
        return this.http.post(this.endpointUrl + "/", entities[0])
            .map((response) => {
                return response;
            })
            .catch((error) => {
                return Observable.throw(error);
            })
    }

    readParametro(id: number): string {
        var parametros = [];
        try {
            parametros = JSON.parse(localStorage.getItem('site_token'));
            for (let i = 0; i < parametros.length; i++) {
                if (parametros[i].id == id) {
                    return parametros[i].valor;
                }
            }
        }
        catch(e) {

        }
        return "";
    }


}