import { Injectable } from '@angular/core';
import { IUsuario } from './interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../environments/environment';
import { AbstractRestService } from '../../../shared/abstract/rest.service';

const API_URL = environment.apiUrl;

@Injectable()
export class UsuarioService extends AbstractRestService<IUsuario> {
    
    constructor(protected http: HttpClient ) {
        super(http)
        this.endpointUrl = API_URL + "/usuarios"
    }

    resetPassword(entity: IUsuario): Observable<IUsuario> {
        return this.http.post(this.endpointUrl + "/public/reset-password", entity)
            .map((response) => {
                return response;
            })
            .catch((error) => {
                return Observable.throw(error);
            })
    }

    changePassword(entity: IUsuario): Observable<IUsuario> {
        return this.http.post(this.endpointUrl + "/change-password", entity)
            .map((response) => {
                return response;
            })
            .catch((error) => {
                return Observable.throw(error);
            })
    }

}
