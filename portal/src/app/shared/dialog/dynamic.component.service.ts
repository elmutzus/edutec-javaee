import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subject }    from 'rxjs/Subject';
import { AbstractDialogContent } from './abstract.dialog.content';
import { ICatalog } from '../catalog/interface';
import { IEntity } from '../interface/entity';

// Componentes de contenido
import { ListParametrosComponent } from '../parametros/list.component';
import { AlertDialogComponent } from './alert/component';
import { ConfirmDialogComponent } from './confirm/component';

@Injectable()
export class DynamicComponentService {
    getComponent(componentName:string): any {
        switch (componentName) {
            case "ListParametrosComponent":
                return ListParametrosComponent;
            case "AlertDialogComponent":
                return AlertDialogComponent;
            case "ConfirmDialogComponent":
                return ConfirmDialogComponent;
        }
    }
}

