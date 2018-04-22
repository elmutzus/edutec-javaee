import { Component, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ICiclo } from './interface';
import { CicloService } from './service';
import { AbstractDialogContent } from '../../../shared/dialog/abstract.dialog.content';
import { ModalDialogService } from '../../../shared/dialog/service';
import { Observable } from 'rxjs/Rx';

@Component({
    templateUrl: './edit.component.html',
})
export class EditCicloComponent extends AbstractDialogContent<ICiclo, CicloService> {
    
    constructor(protected router: Router,
        protected route: ActivatedRoute, 
        protected service: CicloService,       
        protected dialogService: ModalDialogService) {
        super(router, route, service, dialogService);
        this.redirectUrl = '/admin/catalogos/ciclos';
    }
    
    ngOnInit() {
        this.fetchEntity();
    }

    initEntity() {
        this.entity = {
            id: null,
            codigo: ""
        }
    }

}
