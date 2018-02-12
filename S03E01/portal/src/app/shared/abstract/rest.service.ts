import { Observable } from 'rxjs/Observable'
import { HttpClient } from '@angular/common/http'
import { IEntity } from '../interface/entity'
import { IConteo } from '../../shared/interface/count'
import { environment } from '../../../environments/environment'

const API_URL = environment.apiUrl

export abstract class AbstractRestService<E extends IEntity> { 
    
    protected endpointUrl: string

    constructor(protected http: HttpClient) {
    }

    getEntity(id:number): Observable<E> {
        return this.http.get(this.endpointUrl + "/" + id)
        .map((response:E) => {
            return response
        })
        .catch((error) => {
            return Observable.throw(error.statusText)
        })
    }

    getEntities(): Observable<E[]> {
        return this.http.get(this.endpointUrl)
        .map((response:E[]) => {
            return response
        })
        .catch((error) => {
            return Observable.throw(error.statusText)
        })
    }

    getEntitiesPaged(from:number, to:number): Observable<E[]> {
        return this.http.get(this.endpointUrl + "/" + from + "/" + to)
            .map((response) => {
                return response
            })
            .catch((error : Response | any) => {
                return Observable.throw(error.statusText)
            })
    }

    count(): Observable<IConteo> {
        return this.http.get(this.endpointUrl + "/count")
            .map((response) => {
                return response
            })
            .catch((error) => {
                return Observable.throw(error.statusText)
            })
    }

    save(entity: E): Observable<E> {
        return this.http.post(this.endpointUrl, entity)
            .map((response) => {
                return response
            })
            .catch((error) => {
                return Observable.throw(error)
            })
    }

    update(entity: E): Observable<E> {
        // en el caso de herencia de entities
        delete entity["type"];
        return this.http.put(this.endpointUrl + "/" + entity.id, entity)
        .map((response) => {
            return response
        })
        .catch((error) => {
            return Observable.throw(error.statusText)
        })
    }

    delete(entity: E): Observable<E> {
        return this.http.delete(this.endpointUrl + '/' + entity.id)
            .map((response) => {
                return response
            })
            .catch((error) => {
                return Observable.throw(error.statusText)
            })
    }

}