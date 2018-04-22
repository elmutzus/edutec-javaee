import { BrowserModule }                       from '@angular/platform-browser';
import { NgModule }                            from '@angular/core';
import { FormsModule, ReactiveFormsModule  }   from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule }             from '@angular/platform-browser/animations';
import { AppComponent }                        from './app.component';
import { MaterialModule }                      from './material.module';
import { FlexLayoutModule }                    from '@angular/flex-layout';
import { ModalDialogModule }                   from './shared/dialog/module';
import { AppRoutingModule }                    from './app-routing.module';
import { DataGridComponent }                   from './shared/datagrid/component';
import { ListParametrosComponent }             from './shared/parametros/list.component';
import { HomeComponent }                       from './home/component';
import { }                                     from './shared/';
import { ListRolesComponent }                  from './admin/seguridad/roles/list.component';
import { ListUsuariosComponent }               from './admin/seguridad/usuarios/list.component';
import { EditUsuarioComponent }                from './admin/seguridad/usuarios/edit.component';

import { ListCiclosComponent }                 from './admin/catalogos/ciclos/list.component';
import { EditCicloComponent }                  from './admin/catalogos/ciclos/edit.component';
import { ListCursosComponent }                 from './admin/catalogos/cursos/list.component';
import { EditCursoComponent }                  from './admin/catalogos/cursos/edit.component';
import { ListSalonesComponent }                from './admin/catalogos/salones/list.component';
import { EditSalonComponent }                  from './admin/catalogos/salones/edit.component';
import { ListSedesComponent }                  from './admin/catalogos/sedes/list.component';
import { EditSedeComponent }                   from './admin/catalogos/sedes/edit.component';

// Providers
import { RequestResponseInterceptor }    from './shared/interceptors/request.response.interceptor';
import { ParametroService }              from './shared/parametros/service';

import { CicloService }                  from './admin/catalogos/ciclos/service';
import { CursoService }                  from './admin/catalogos/cursos/service';
import { DepartamentoService }           from './admin/catalogos/departamentos/service';
import { MunicipioService }              from './admin/catalogos/municipios/service';
import { SalonService }                  from './admin/catalogos/salones/service';
import { SedeService }                   from './admin/catalogos/sedes/service';
import { RolService }                    from './admin/seguridad/roles/service';
import { UsuarioService }                from './admin/seguridad/usuarios/service';


@NgModule({
    declarations: [
        AppComponent,
        DataGridComponent,
        ListParametrosComponent,
        HomeComponent,
        ListCiclosComponent,
        EditCicloComponent,
        ListCursosComponent,
        EditCursoComponent,
        ListSalonesComponent,
        EditSalonComponent,
        ListSedesComponent,
        EditSedeComponent,
        ListRolesComponent,
        ListUsuariosComponent,
        EditUsuarioComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule ,
        HttpClientModule,
        MaterialModule,
        FlexLayoutModule,
        ModalDialogModule
    ],

    providers: [
        ParametroService,
        CicloService,
        CursoService,
        DepartamentoService,
        MunicipioService,
        SalonService,
        SedeService,
        RolService,
        UsuarioService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: RequestResponseInterceptor,
            multi: true,
        },        
    ],
    entryComponents: [
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
