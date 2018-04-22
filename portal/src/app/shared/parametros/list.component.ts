import { Component } from '@angular/core'
import { Observable } from 'rxjs/Observable'
import { IParametro } from './interface'
import { IConteo } from '../../shared/interface/count'
import { ParametroService } from './service'
import { ModalDialogService } from '../dialog/service';

@Component({
    templateUrl: "./list.component.html",
    styleUrls:['./style.css']
})
export class ListParametrosComponent {
    entityList: IParametro[]
    busy = false
    insideDialog: boolean = false
    
    constructor(private service: ParametroService, protected dialogService: ModalDialogService) {}

    ngOnInit() {
        this.entityList = []
        this.fetchPage()       
    }

    setGroups() {
        window.scrollTo(0,0)
        for (let param of this.entityList) {
            param.grupo = 'general'
            /*if (param.id <= 4)
                param.grupo = 'general'
            else if ((param.id >= 5 && param.id <= 16) || param.id == 24 || param.id == 25) 
                param.grupo = 'smtp'
            else if (param.id >= 17 && param.id <= 23) 
                param.grupo = 'ldap'*/
        }
    }

    fetchPage() {
        this.busy = true
        this.service.getEntities().subscribe((response) => {
            this.busy = false
            this.entityList = response
            this.setGroups()
        }, (err) => {
            this.busy = false
            console.dir (err)
            console.dir ("Error en la peticion")
        })
    }


    protected handleSaveError(response: any) {
        this.busy = false;
        this.dialogService.setBusy(false);
    }

    save() {
        console.dir ( this.entityList )
        this.busy = true
        this.service.saveMany(this.entityList).subscribe((response)=>{
            this.busy = false
            this.entityList = response
            this.setGroups()
            //wasCanceled
            this.dialogService.closeEvent(false)
        },
        (err)=>{
            this.busy = false
        })
    }

    initOnDialog() {
        this.insideDialog = true;
    }
}
