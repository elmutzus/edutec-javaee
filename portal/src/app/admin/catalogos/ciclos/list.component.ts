import { Component } from '@angular/core';
import { ICiclo } from './interface';
import { CicloService } from './service';
import { IDatagridOptions } from '../../../shared/datagrid/interface'
import { DataGridComponent } from '../../../shared/datagrid/component';

@Component({
    template: `
        <div>
            <datagrid [options]="childOptions" [service]="service"></datagrid>
        </div>
    `
})
export class ListCiclosComponent {
    childOptions:  IDatagridOptions;
    
    constructor(public service: CicloService) {
    }

    ngOnInit() {
        this.childOptions = {
            pageSize: 20, 
            title: "Ciclos",
            baseUrl: "/admin/catalogos/ciclos",
            columns: [
                { name: "id", caption: "Id", sortDirection: 0, width: "auto", sortable: false },
                { name: "codigo", caption: "Código", sortDirection: 0, width: "50%", sortable: false }
            ],
            showHeader: true,
            editable: true
        }
    }
}
