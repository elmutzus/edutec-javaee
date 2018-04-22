import { NgModule }                          from '@angular/core';
import { CommonModule }                      from '@angular/common';
import { ModalDialogComponent }              from './component';
import { ModalDialogService }                from './service';
import { DynamicComponentService }           from './dynamic.component.service';
import { DynamicComponentHostDirective }     from './directive';

// Componentes de contenido
import { ListParametrosComponent } from '../parametros/list.component';
import { AlertDialogComponent }              from './alert/component';
import { ConfirmDialogComponent }            from './confirm/component';


import { MatButtonModule } from '@angular/material';

@NgModule({
    declarations: [
        ModalDialogComponent,
        DynamicComponentHostDirective,
        AlertDialogComponent,
        ConfirmDialogComponent
    ],
    exports: [
        ModalDialogComponent,
        DynamicComponentHostDirective
    ],
    imports: [
        CommonModule,
        MatButtonModule
    ],
    providers: [
        ModalDialogService,
        DynamicComponentService
    ],
    entryComponents: [
        ListParametrosComponent,
        AlertDialogComponent,
        ConfirmDialogComponent
    ]
})
export class ModalDialogModule { }