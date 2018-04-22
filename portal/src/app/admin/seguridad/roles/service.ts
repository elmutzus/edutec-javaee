import { Injectable } from '@angular/core';
import { IRol } from './interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../../environments/environment';
import { AbstractRestService } from '../../../shared/abstract/rest.service';

const API_URL = environment.apiUrl;

@Injectable()
export class RolService extends AbstractRestService<IRol> {
	
    constructor(protected http: HttpClient ) {
        super(http)
        this.endpointUrl = API_URL + "/roles"
    }

}
