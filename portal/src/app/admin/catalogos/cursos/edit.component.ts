import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ISede } from './interface';
import { IMunicipio } from '../municipios/interface';
import { CursoService } from './service';
import { MunicipioService } from '../municipios/service';
import { AbstractDialogContent } from '../../../shared/dialog/abstract.dialog.content';
import { IModalDialogOptions } from '../../../shared/dialog/interface';
import { ModalDialogService } from '../../../shared/dialog/service';
import { Observable } from 'rxjs/Rx';
import { ISubscription } from 'rxjs/Subscription';

@Component({
    templateUrl: './edit.component.html',
})
export class EditCursoComponent extends AbstractDialogContent<ISede, CursoService> {
    unidades: IMunicipio[];
    
    constructor(protected router: Router,
        protected route: ActivatedRoute, 
        protected service: CursoService,
        protected dialogService: ModalDialogService,
        protected municipioService: MunicipioService) {
        super(router, route, service, dialogService);
        this.redirectUrl = '/admin/catalogos/sedes';
    }
    
    ngOnInit() {
        this.fetchEntity();
    }

    initEntity() {
        this.entity = {
            id: null,
            nombre: "",
            codigo: "",
            direccion: "",          
            municipio: {
                id: null,
                codigo: "",
                nombre: ""
            }
        }
        this.getMunicipios();   
    }

    getMunicipios () {
        this.busy = true;
        this.municipioService.getEntities().subscribe((response)=>{
            this.busy = false;
            this.unidades = response;
        }, (err) => {
            this.busy = false;
        });
    }

}
