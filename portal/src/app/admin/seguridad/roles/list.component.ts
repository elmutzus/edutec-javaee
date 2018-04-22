import { Component } from '@angular/core';
import { Router } from '@angular/router'
import { IRol } from './interface';
import { RolService } from './service';
import { IDatagridOptions } from '../../../shared/datagrid/interface'
import { DataGridComponent } from '../../../shared/datagrid/component';
import { ModalDialogService } from '../../../shared/dialog/service'

@Component({
    template: `
        <div>
            <datagrid [options]="options" [service]="service"></datagrid>
        </div>
    `
})
export class ListRolesComponent {
    options:  IDatagridOptions;

    constructor(
        public service: RolService, 
        protected router: Router,
        protected dialogService: ModalDialogService,
        ) {
        
    }

    ngOnInit() {
        this.options = {
            pageSize: 20, 
            title: "Roles",
            baseUrl: "/admin/seguridad/roles",
            columns: [
                { name: "nombre", caption: "Nombre", sortDirection: 0, width: "auto", sortable: false },
                { name: "descripcion", caption: "Descripcion", sortDirection: 0, width: "auto", sortable: false }
            ],
            showHeader: true,
            editable: false            
        }
    }
    
}
