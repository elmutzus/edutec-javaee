import { Component } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http'
import { IModalDialogOptions } from './shared/dialog/interface';
import { ModalDialogService } from './shared/dialog/service';

const API_URL = environment.apiUrl;

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent {
    title = 'Java EE + Angular 5'
    parametros = []
    lastOpenDialog: string = ""
    private parametrosResource = API_URL + '/parametros'

    constructor(
        private dialogService: ModalDialogService, 
        private httpClient: HttpClient) {    
    }

    ngOnInit() { 
        this.getParametrosSistema();
    }

    getParametrosSistema() {
        return this.httpClient.get(this.parametrosResource)
            .map((responseData:any[])=> responseData )
            .subscribe(data => {
                this.parametros = data
                if (this.parametros.length > 0) {
                    this.title = this.parametros[1].valor
                }
            })
    }

    openDialog(componentName: string, dialogTitle: string) {
        this.lastOpenDialog = componentName
        this.dialogService.showDialog({
            componentClassName: componentName,
            title: dialogTitle,
            bodyExtraClass: "",
            size: "modal-lg",
            readOnly: false
        })
    }

}

