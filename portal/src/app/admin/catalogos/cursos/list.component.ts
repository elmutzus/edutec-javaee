import { Component } from '@angular/core';
import { ISede } from './interface';
import { CursoService } from './service';
import { IDatagridOptions } from '../../../shared/datagrid/interface'
import { DataGridComponent } from '../../../shared/datagrid/component';

@Component({
    template: `
        <div>
            <datagrid [options]="childOptions" [service]="service"></datagrid>
        </div>
    `
})
export class ListCursosComponent {
    childOptions:  IDatagridOptions;
    
    constructor(public service: CursoService) {
    }

    ngOnInit() {
        this.childOptions = {
            pageSize: 20, 
            title: "Sedes",
            baseUrl: "/admin/catalogos/sedes",
            columns: [
                { name: "id", caption: "Id", sortDirection: 0, width: "5%", sortable: false },
                { name: "nombre", caption: "Nombre", sortDirection: 0, width: "auto", sortable: false},
                { name: "descripcion", caption: "Descripción", sortDirection: 0, width: "40%", sortable: false },
                { name: "unidad.descripcion", caption: "Unidad de Medida", sortDirection: 0, width: "auto", sortable: false}
            ],
            showHeader: true,
            editable: true
        }
    }
}
