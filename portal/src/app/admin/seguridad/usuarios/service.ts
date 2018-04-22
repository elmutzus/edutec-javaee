import { Injectable } from '@angular/core';
import { IUsuario } from './interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../environments/environment';
const API_URL = environment.apiUrl;

@Injectable()
export class UsuarioService {

	private endpointUrl = API_URL + "/usuarios";

  	constructor(private http: HttpClient) {

  	}

	getUsuarios(): Observable<IUsuario[]> {
		return this.http.get(this.endpointUrl)
		.map((response) => {
			return response;
		})
		.catch((error) => {
			return Observable.throw(error.statusText);
		});
	}

	getUsuario(id:number): Observable<IUsuario> {
		return this.http.get(this.endpointUrl + "/" + id)
		.map((response) => {
			return response;
		})
		.catch((error) => {
			return Observable.throw(error.statusText);
		});
	}

	update(usuario: IUsuario): Observable<IUsuario> {
		return this.http.put(this.endpointUrl + "/", usuario)
		.map((response) => {
			return response;
		})
		.catch((error) => {
			return Observable.throw(error.statusText);
		});
	}
	
	save(usuario: IUsuario): Observable<IUsuario> {
		return this.http.post(this.endpointUrl + "/", usuario)
		.map((response) => {
			return response;
		})
		.catch((error) => {
			return Observable.throw(error.statusText);
		});
	}

	delete(idUsuario: Number): Observable<IUsuario> {
		return this.http.delete(this.endpointUrl + "/" + idUsuario)
		.map((response) => {
			return response;
		})
		.catch((error) => {
			return Observable.throw(error.statusText);
		});
	}

}
