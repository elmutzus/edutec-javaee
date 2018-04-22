import { Component, Input } from '@angular/core'
import { Observable } from 'rxjs/Observable'
import { IEntity } from '../interface/entity'
import { IConteo } from '../interface/count'
import { IDatagridOptions } from './interface'
import { ModalDialogService } from '../dialog/service'
import { AbstractRestService } from '../abstract/rest.service'
import { Router } from '@angular/router';

@Component({
    selector: "datagrid",
    templateUrl: "./component.html",
    styleUrls:["component.css"]
})
export class DataGridComponent {

    @Input() 
    options: IDatagridOptions
    
    @Input()
    service: AbstractRestService<IEntity>

    entityList: IEntity[]
    busy = false
    sorting = false
    pages = []
    curPage: number = 0
    pageSize: number = 20
    sortColumn: string = ""
    sortIconClass: string[] = []
    lastOpenDialog: string = ""

    constructor(protected dialogService: ModalDialogService, protected router: Router) {
    }
    objectResolve = function(path, obj) {
        return path.split('.').reduce(function(prev, curr) {
            return prev ? prev[curr] : undefined
        }, obj || self)
    }

    ngOnInit() {
        if (this.options.pageSize && this.options.pageSize > 0)
            this.pageSize = this.options.pageSize
        this.curPage = 0
        this.entityList = []
        this.sortIconClass[-1] = "sorting_desc"
        this.sortIconClass[0] = "sorting"
        this.sortIconClass[1] = "sorting_asc"
        this.fetchPage(1)
    }
    
    fetchPage(pageNum: number) {
        this.busy = true
        this.curPage = pageNum
        this.service.getEntities().subscribe((response) => {
                this.busy = false
                this.entityList = response
                //console.dir (this.entityList)
            }, (err) => {
                this.busy = false
                //console.dir ("Error en la peticion")
                console.dir (err)
            })
    }

    editarEntity<E extends IEntity>(entity: E)  {
        if (!entity || !entity.id || entity.id == null)
            this.router.navigate([this.options.baseUrl + "/agregar"]);
        else 
            this.router.navigate([this.options.baseUrl + "/edit/" + entity.id]);                
    }

    eliminar<E extends IEntity>(entity: E) {
        this.dialogService.confirm({
            title:"Confirmación",
            message: 'Ésta operación es irreversible. Está Seguro?',
            yesCallback: ()=>{
                this.busy = true
                this.service.delete(entity).subscribe((response)=>{
                    this.busy = false
                    this.ngOnInit()
                }, (err) => {           
                    this.busy = false
                })
            },
            noCallback: ()=>{
                // Do nothing
                console.dir ("No quiso :(")
            }
        })
    }

}
