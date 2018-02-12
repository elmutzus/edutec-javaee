import { Injectable, Injector } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { IErrorMessage } from './error.message.interface';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { NgProgress } from 'ngx-progressbar';
import { ModalDialogService } from '../dialog/service';
import 'rxjs/add/operator/do';
import { MatSnackBar } from '@angular/material';

@Injectable()
export class RequestResponseInterceptor implements HttpInterceptor {
    
    PATHS_WITHOUT_ALERT = []

    constructor(protected inj: Injector, 
        public router: Router,
        public ngProgress: NgProgress,
        public dialogService: ModalDialogService,
        public snackBar: MatSnackBar) {
    }
    
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let errorDto: IErrorMessage;

        if (request.url.includes("login"))
            return next.handle(request);

        let headers : {} = {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        if (request.url == '/edutec-javaee/api/') {
            headers = {
                'Content-Type': 'text/html; charset=UTF-8'
            }
        }

        let clonedRequest = request.clone({
            setHeaders: headers
        });
        
        this.ngProgress.start();
        
		return next.handle(clonedRequest)
        .do(event => {
            //console.dir (event);
            if (event instanceof HttpResponse) {
                this.ngProgress.done();
                let message: string = "Operación exitosa";
                if (event.body.mensaje)
                    message += ". " + event.body.mensaje

                if (event.ok && (request.method == 'POST' || request.method == 'PUT' || request.method == 'DELETE')) {
                    let showAlert = true
                    for (let path of this.PATHS_WITHOUT_ALERT) {
                        if (request.url.includes(path)) {
                            showAlert = false
                            break
                        }
                    }

                    if (showAlert) {
                        let snackBarRef = this.snackBar.open(message, "", { duration: 2000 });
                    }
                }
            }
        })
        .catch((err: HttpErrorResponse) => {
            this.ngProgress.done();
            if (err && err.error instanceof Error) {
                console.log('An error occurred:', err.error.message);
            } else if (err) {
                console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
                console.dir (err);
                if (err instanceof HttpErrorResponse && err.status == 401) {
                    //this.alertService.alert(AlertType.Warning, "Sesión Expirada");
                    this.dialogService.closeEvent(true);
                }
                else {
                    let customErrorMessage = "";
                    if (typeof err.error == 'object')
                        errorDto = err.error;
                    else
                        errorDto = JSON.parse(err.error);
                    //console.dir (errorDto);
                    if (err.status == 415) {
                        customErrorMessage = "Formato no soportado.";
                    }
                    else if (err.status == 500) {
                        customErrorMessage = "Ocurrió un error inesperado.";
                    }                    
                    if (errorDto.mensaje)
                        customErrorMessage = errorDto.mensaje;

                    //this.alertService.alert(customAlertType, customErrorMessage);
                    // snackBar
                }
            }
            return Observable.throw(err);
        });

   }
}