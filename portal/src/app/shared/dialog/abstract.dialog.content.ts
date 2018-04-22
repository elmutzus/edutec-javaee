import { ModalDialogService } from './service';
import { IModalDialogOptions } from './interface'
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { IEntity } from '../interface/entity';
import { AbstractRestService } from '../abstract/rest.service';
import { ICatalog } from '../catalog/interface';

/*
    E: objeto de tipo entidad como: IUsuario extiende de IEntity
    S: objecto de tipo servicio como: UsuarioService extiende de AbstractRestService
*/
export abstract class AbstractDialogContent<E extends IEntity, S extends AbstractRestService<E>> implements ICatalog {
    id: number;
    entity: E;
    readOnly: boolean = false;
    busy: boolean = false;
    redirectUrl: string;
    insideDialog: boolean = false;
    protected sub: any;

    constructor(protected router: Router,
                protected route: ActivatedRoute, 
                protected service: S,
                protected dialogService: ModalDialogService) {
        console.log ("Construtor abstracto de AbstractDialogContent");
    }

    // Inicializar la entity
    abstract initEntity(): void;
    
    /*
      Relativos a dialog
    */
    // Inicializar valores cuando esta dentro del dialogo
    // Es utilizado por el servicio de creacion de componentes
    public initOnDialog(options: IModalDialogOptions):void {
        this.insideDialog = true;
        this.initEntity()
        this.readOnly = false
        if (options.data && options.data.id) {
            this.id = options.data.id
            this.readOnly = false
            this.busy = true        
            this.service
                .getEntity(this.id)
                .subscribe((response) => {
                    this.busy = false;
                    this.entity = response;
                    //this.postFetchEntity();
                    this.readOnly = false;
                }, (err) => {
                    this.busy = false;
                });            
        }
    }

    protected closeParentDialog(wasCanceled: boolean):void {
        // Desactivar los controles al usuario
        this.readOnly = true;

        let _this = this;
        let sleepTime = 500;
        
        // Si cancelo, no debe esperar
        if (wasCanceled)
            sleepTime = 0;

        // Esperar para que el usuario vea el cambio y el mensaje
        setTimeout(function(){
            _this.dialogService.closeEvent(wasCanceled);
        },sleepTime);
    }

    /*
      Utils
    */
    public resolveRedirect(wasCanceled:boolean) {
        if (this.insideDialog)
            this.closeParentDialog(wasCanceled);
        else
            this.router.navigate([this.redirectUrl]);
    }
    protected handleSaveSuccess(response: any) {
        this.busy = false;
        this.dialogService.setBusy(false);
        this.entity = response;
        this.resolveRedirect(false);
    }
    protected handleSaveError(response: any) {
        this.busy = false;
        this.dialogService.setBusy(false);
    }

    /*
      Acciones
    */
    protected postFetchEntity() { }
    protected fetchEntity() {
        this.initEntity();
        if (!this.insideDialog) {
            if (!this.router.url.includes("/edit/") && !this.router.url.includes("/agregar"))
                this.readOnly = true;
        }
        this.sub = this.route.params.subscribe(params => {
            this.id = +params['id'];
            if (this.id && this.id != null) {
                this.busy = true;
                this.service
                    .getEntity(this.id)
                    .subscribe((response) => {
                        this.busy = false;
                        this.entity = response;
                        this.postFetchEntity();
                    }, (err) => {
                        this.busy = false;
                    });
            }
        });
    }
    protected cancel() {
        this.resolveRedirect(true);
    }
    protected preSave() { }
    public save(postEntity?: E) {
        this.preSave();
        if (!postEntity || postEntity == null)
            postEntity = this.entity;
        this.busy = true;
        this.dialogService.setBusy(true);
        if (this.entity.id == null) {
            this.service.save(postEntity).subscribe(
                (response) => this.handleSaveSuccess(response), 
                (err) => this.handleSaveError(err)
            );
        }
        else {
            this.service.update(postEntity).subscribe(
                (response) => this.handleSaveSuccess(response),
                (err) => this.handleSaveError(err)
            );
        }
    }

}
