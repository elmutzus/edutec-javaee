import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, Subscription } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { FormControl, Validators } from '@angular/forms';
import { IElement } from '../app.interfate';
import { Router } from '@angular/router';
import { IModalDialogOptions } from '../shared/dialog/interface';
import { ModalDialogService } from '../shared/dialog/service';

const API_URL = environment.apiUrl;

@Component({
     templateUrl: './component.html'
})
export class HomeComponent {
    private greetingsResource = API_URL + '/'
    private parametrosResource = API_URL + '/parametros'

    lastOpenDialog: string = ""
    busy: boolean = false
    parametros: any[] = []
    greetingsText: String = ""

    constructor(private httpClient: HttpClient, private router: Router, private dialogService: ModalDialogService) {
        
    }

    ngOnInit() {
        this.greetings()
        this.getParametros()
    }

    greetings() {
        return this.httpClient.get(this.greetingsResource)
            .map((responseData:String)=> responseData )
            .subscribe(data => {
                alert(data)
                this.greetingsText = data
            }, (err)=> {
                this.greetingsText = err.error.text
            })
    }

    getParametros() {
        return this.httpClient.get(this.parametrosResource)
            .map((responseData:any[])=> responseData )
            .subscribe(data => {
                this.parametros = data
            }, (err)=> {
                
            })
    }
    

    getKpis() {

    }

    // En dialogo
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

