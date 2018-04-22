import { Injectable } from '@angular/core';
import { IMunicipio } from './interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../environments/environment';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
const API_URL = environment.apiUrl;

@Injectable()
export class MunicipioService {
	private endpointUrl = API_URL + "/municipios";
  	constructor(private http: HttpClient) { }

	getEntities(): Observable<IMunicipio[]> {
		return this.http.get(this.endpointUrl )
		.map((response) => {
			return response;
		})
		.catch((error : Response | any) => {
			return Observable.throw(error.statusText);
		});
	}

}