import { Component } from '@angular/core';
import { ISalon } from './interface';
import { SalonService } from './service';
import { IDatagridOptions } from '../../../shared/datagrid/interface'
import { DataGridComponent } from '../../../shared/datagrid/component';

@Component({
    template: `
        <div>
            <datagrid [options]="childOptions" [service]="service"></datagrid>
        </div>
    `
})
export class ListSalonesComponent {
    childOptions:  IDatagridOptions;
    
    constructor(public service: SalonService) {
    }

    ngOnInit() {
        this.childOptions = {
            pageSize: 20, 
            title: "Salones",
            baseUrl: "/admin/catalogos/salones",
            columns: [
                { name: "id", caption: "Id", sortDirection: 0, width: "5%", sortable: false },
                { name: "codigo", caption: "Nombre", sortDirection: 0, width: "auto", sortable: false},
                { name: "sede.codigo", caption: "Sede", sortDirection: 0, width: "auto", sortable: false}
            ],
            showHeader: true,
            editable: true
        }
    }
}
