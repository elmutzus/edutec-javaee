import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractDialogContent } from '../../../shared/dialog/abstract.dialog.content';
import { ModalDialogService } from '../../../shared/dialog/service';
import { IUsuario } from './interface';
import { IRol } from '../roles/interface';
import { UsuarioService } from './service';
import { RolService } from '../roles/service';
import { Observable } from 'rxjs/Rx';

@Component({
  templateUrl: './edit.component.html',
})
export class EditUsuarioComponent extends AbstractDialogContent<IUsuario, UsuarioService> {
    roles: IRol[];
    
    constructor(protected router: Router,
        protected route: ActivatedRoute, 
        protected service: UsuarioService,      
        protected dialogService: ModalDialogService,
        private rolService: RolService) {
        super(router, route, service, dialogService);
        this.redirectUrl = '/admin/seguridad/usuarios';
    }
    
    ngOnInit() {
        this.readOnly = false
        this.initEntity()
        this.fetchEntity()
        if (!this.readOnly) {
            this.rolService
                .getRoles()
                .subscribe((response) => {
                    this.roles = response;
                }, (err) => {
                });
        }
    }

    initEntity() {
        this.entity = {
            id: null,
            activo: 1,
            clave: "",
            email: "",
            nombre: "",
            rol: {
                id: null,
                nombre: "",
                descripcion: ""
            },
            telefono: ""
        }       
    }
    
    fetchEntity() {
        
        if (!this.router.url.includes("/edit/") && !this.router.url.includes("/agregar"))
            this.readOnly = true
        
        this.route.params.subscribe(params => {
            this.id = +params['id'];

            if (this.id && this.id != null) {
                this.busy = true;
                this.service
                    .getEntity(this.id)
                    .subscribe((response) => {
                        this.busy = false;
                        this.entity = response;
                    }, (err) => {
                        this.busy = false;
                    });
            }
        });
    }

}
