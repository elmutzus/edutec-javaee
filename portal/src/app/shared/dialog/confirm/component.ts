import { Component } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IModalDialogOptions } from '../interface'
import { ISimpleDialog } from '../interface'
import { ModalDialogService } from '../service'

@Component({
	templateUrl: "./component.html"
})
export class ConfirmDialogComponent implements ISimpleDialog {
	busy = false
	options: IModalDialogOptions
    
    constructor(private dialogService: ModalDialogService) {}

    ngOnInit() {
		
	}

	show(options: IModalDialogOptions):boolean {
		this.options = options
		return true
	}

	// Acciones
	yesClick() {
		this.dialogService.closeEvent(false);
		if (this.options.yesCallback)
			this.options.yesCallback()
	}
	noClick() {
		this.dialogService.closeEvent(false);
		if (this.options.noCallback)
			this.options.noCallback()
	}
	cancelClick() {
		this.dialogService.closeEvent(true);
		if (this.options.cancelCallback)
			this.options.cancelCallback()
	}
	
}
