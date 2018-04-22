import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IUsuario } from './interface';
import { IRol } from '../roles/interface';
import { UsuarioService } from './service';
import { RolService } from '../roles/service';
import { Observable } from 'rxjs/Rx';

@Component({
    templateUrl: './edit.component.html',
})
export class EditUsuarioComponent {
 	id: number;
	private sub: any;
	entity: IUsuario;
	readOnly = true;
	roles: IRol[];
	busy = false;

 	constructor(private route: ActivatedRoute,
 		private usuarioService: UsuarioService,
 		private rolService: RolService,
 		private router: Router) {}

    ngOnInit() {
    	this.entity = {
    		 nombre: "",
    		 email: "",
    		 id: null,
             telefono: "0",
    		 rol: {
    		 	id: null,
    		 	nombre: "",
    		 	descripcion: ""
             }
    	}
		this.sub = this.route.params.subscribe(params => {
	       	this.id = +params['id'];
			if (this.id && this.id != null) {
				this.busy = true;
				this.usuarioService
					.getUsuario(this.id)
					.subscribe((response) => {
						this.entity = response;

						this.rolService
							.getEntities()
							.subscribe((response) => {
								this.roles = response;
								for (let rol of this.roles) {
									if (this.entity.rol.id == rol.id) {
										this.entity.rol = rol;
										break;
									}
								}
								this.busy = false;
							}, (err) => {
								this.busy = false;
								//this.handleError
							});

					}, (err) => {
						this.busy = false;
					});
			} else {
				this.rolService
					.getEntities()
					.subscribe((response) => {
						this.roles = response;
						this.busy = false;
					}, (err) => {
						this.busy = false;
					});
			}
	    });
    }

	save() {
		this.busy = true;
		if (this.entity.id && this.entity.id != null) {
			this.usuarioService.update(this.entity).subscribe((response) => {
				this.busy = false;
				this.entity = response;
			}, (err) => {
				this.busy = false;
			});
		}
		else {
			this.usuarioService.save(this.entity).subscribe((response) => {
					this.busy = false;
					this.entity = response;
				}, (err) => {
					this.busy = false;
				});			
		}

		this.router.navigate(['/admin/usuarios']);
	}
	cancel() {
		this.router.navigate(['/admin/usuarios']);
	}

    private handleError (error: any) {
    	//console.dir ("usuario no encontrado");
    	this.busy = false;
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }


}
