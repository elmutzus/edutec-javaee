import { Injectable } from '@angular/core';
import { IRol } from './interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../environments/environment';
const API_URL = environment.apiUrl;

@Injectable()
export class RolService {
	
	private endpointUrl = API_URL + "/roles";

  	constructor(private http: HttpClient) {

  	}

	getRoles(): Observable<IRol[]> {
		return this.http.get(this.endpointUrl)
		.map((response) => {
			return response;
		})
		.catch((error) => {
			return Observable.throw(error.statusText);
		});
	}

}
