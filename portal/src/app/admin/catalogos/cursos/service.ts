import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { ISede } from './interface';
import { IConteo } from '../../../shared/interface/count';
import { AbstractRestService } from '../../../shared/abstract/rest.service';

const API_URL = environment.apiUrl;

@Injectable()
export class CursoService extends AbstractRestService<ISede> {
    
    constructor(protected http: HttpClient) {
        super(http)
        this.endpointUrl = API_URL + "/cursos";
    }

}
