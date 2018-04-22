import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ISalon } from './interface';
import { ISede } from '../sedes/interface';
import { SalonService } from './service';
import { SedeService } from '../sedes/service';
import { AbstractDialogContent } from '../../../shared/dialog/abstract.dialog.content';
import { IModalDialogOptions } from '../../../shared/dialog/interface';
import { ModalDialogService } from '../../../shared/dialog/service';
import { Observable } from 'rxjs/Rx';
import { ISubscription } from 'rxjs/Subscription';

@Component({
    templateUrl: './edit.component.html',
})
export class EditSalonComponent extends AbstractDialogContent<ISalon, SalonService> {
    unidades: ISede[];
    
    constructor(protected router: Router,
        protected route: ActivatedRoute, 
        protected service: SalonService,
        protected dialogService: ModalDialogService,
        protected sedeService: SedeService) {
        super(router, route, service, dialogService);
        this.redirectUrl = '/admin/catalogos/salones';
    }
    
    ngOnInit() {
        this.fetchEntity();
    }

    initEntity() {
        this.entity = {
            id: null,
            codigo: "",            
            sede: {
                id: null,
                codigo: "",
                nombre: "",
                direccion: "",
                municipio: null
            }
        }
        this.getSedes();   
    }

    getSedes () {
        this.busy = true;
        this.sedeService.getEntities().subscribe((response)=>{
            this.busy = false;
            this.unidades = response;
        }, (err) => {
            this.busy = false;
        });
    }

}
