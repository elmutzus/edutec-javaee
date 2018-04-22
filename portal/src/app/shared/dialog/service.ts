import { Injectable } 			from '@angular/core';
import { Observable } 			from 'rxjs/Observable';
import { Subject } 	  			from 'rxjs/Subject';
import { IModalDialogOptions }	from './interface'

@Injectable()
export class ModalDialogService {
	private subject = new Subject<any>();
	private closeSubject = new Subject<any>();
	private busySubject = new Subject<any>();

  	constructor() {}
	
	onSetBusy(): Observable<any> {
		return this.busySubject.asObservable();
	}
	setBusy(busyState:boolean) {
		this.busySubject.next(busyState)
	}

	onShowDialog(): Observable<any> {
        return this.subject.asObservable();
    }
	showDialog(options: IModalDialogOptions) {
		this.subject.next(options)
    }
	
	// Componentes loosely coupled a los metodos
	confirm(options: IModalDialogOptions):void {
		options.componentClassName = "ConfirmDialogComponent";
		this.showDialog(options)
	}
	alert(options: IModalDialogOptions):void {
		options.componentClassName = "AlertDialogComponent";
		this.showDialog(options)
	}

	// Para suscribirse al cerrar el dialogo
	// El cliente llama closeEvent()
	onCloseDialog(): Observable<any> {
        return this.closeSubject.asObservable();
    }
	closeEvent(wasCanceled: boolean) {
		this.closeSubject.next(wasCanceled);
	}

	//?
	navigateOnDialog(options: IModalDialogOptions) {
		this.subject.next(options)
	}

}