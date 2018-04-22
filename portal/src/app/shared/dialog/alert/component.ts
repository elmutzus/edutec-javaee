import { Component } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IModalDialogOptions } from '../interface'
import { ISimpleDialog } from '../interface'
import { ModalDialogService } from '../service'

@Component({
	templateUrl: "./component.html"
})
export class AlertDialogComponent implements ISimpleDialog {
	busy = false;
	options: IModalDialogOptions;
    
    constructor(private service: ModalDialogService) {}

    ngOnInit() {
		
	}

	show(options: IModalDialogOptions):boolean {
		this.options = options
		return true
	}

}
