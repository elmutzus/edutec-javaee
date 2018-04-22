import { Component } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IUsuario } from './interface';
import { UsuarioService } from './service';
import { ModalDialogService } from '../../../shared/dialog/service';

@Component({
  templateUrl: './list.component.html',
})
export class ListUsuariosComponent {
	usuarios: IUsuario[];
	busy = false;

    constructor(private service: UsuarioService,
        private dialogService: ModalDialogService ) {}

    ngOnInit() {
    	this.busy = true;
		this.service.getUsuarios().subscribe((response) => {
				this.busy = false;
				this.usuarios = response;
			}, (err) => {
				this.busy = false;
				console.dir ("Error en la peticion...");
			});
	}

    eliminar(entity: IUsuario) {
        this.dialogService.confirm({
            title:"Confirmación",
            message: 'Ésta operación es irreversible. Está Seguro?',
            yesCallback: ()=>{
                this.busy = true
                this.service.delete(entity.id).subscribe((response)=>{
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
