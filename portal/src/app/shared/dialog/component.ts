import { Observable } from 'rxjs/Observable';
import { Component, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ISimpleDialog } from './interface'
import { AbstractDialogContent } from './abstract.dialog.content';
import { IModalDialogOptions } from './interface';
import { ModalDialogService } from './service';
import { DynamicComponentService } from './dynamic.component.service';
//import { AuthService } from '../../auth/auth.service';
import { DynamicComponentHostDirective } from './directive';

@Component({
    selector: 'ng-modal-dialog',
    templateUrl: './component.html',
    styleUrls: ['./component.css']
})
export class ModalDialogComponent {
	componentClassName: string = "";
	busy: boolean = false;
	visible: boolean = false;
	title: string = "Titulo del dialogo";
	message: string;
	dialogClassSize: string = "modal-md";
	bodyExtraClass: string = ""
	visibleTitle: boolean = true;
	history: IModalDialogOptions[] = [];


 	@ViewChild(DynamicComponentHostDirective) 
 	componentHost: DynamicComponentHostDirective;

 	constructor(//private auth: AuthService, 
 		public dialogService: ModalDialogService,
 		private dynComponentService: DynamicComponentService,
 		private componentFactoryResolver: ComponentFactoryResolver) {			
	}

	renderComponents(options: IModalDialogOptions) {
		if (this.componentClassName && this.componentClassName != "") {
			let component = this.dynComponentService.getComponent(this.componentClassName);
			let componentFactory = this.componentFactoryResolver.resolveComponentFactory(component);
			let viewContainerRef = this.componentHost.viewContainerRef;
			    viewContainerRef.clear();
			let componentRef = viewContainerRef.createComponent(componentFactory);		
			
			// Inicializar el dialogo
			if (this.componentClassName != 'ConfirmDialogComponent' && this.componentClassName != 'AlertDialogComponent') {				
				this.dialogClassSize = "modal-md"
				if (options.size)
					this.dialogClassSize = options.size
				
				this.bodyExtraClass = ""
				if (options.bodyExtraClass)
					this.bodyExtraClass = options.bodyExtraClass
				
				if (options.visibleTitle == false)
					this.visibleTitle = false;
				(<AbstractDialogContent<any, any>>componentRef.instance).initOnDialog(options)
			} 
			else {
				this.dialogClassSize = "modal-sm center-vertical";
				(<ISimpleDialog>componentRef.instance).show(options);
			};
		}
	}

	ngOnInit() {
        this.dialogService.onShowDialog().subscribe((options: IModalDialogOptions) => {
            if (options) {
	            this.title = options.title;
	            this.componentClassName = options.componentClassName;
	            this.message = options.message
	        }

			this.visible = false;
			setTimeout(()=>{
				this.renderComponents(options);
		        this.visible = true;
			}, 100);
        });
        this.dialogService.onCloseDialog().subscribe((wasCanceled) => {
			this.visible = false;
			setTimeout(()=>{
				let viewContainerRef = this.componentHost.viewContainerRef;
				viewContainerRef.clear();
			},300);

        });
        this.dialogService.onSetBusy().subscribe((busyState) => {
        	this.busy = busyState;
        });
    }

	closeMe() {
		console.log("Me canceló :'(")
		this.dialogService.closeEvent(true)
	}
}
