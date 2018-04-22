import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ListParametrosComponent } from './shared/parametros/list.component';
import { SelectivePreloadingStrategy } from './selective-preloading-strategy';
import { HomeComponent } from './home/component';
import { ListRolesComponent }                  from './admin/seguridad/roles/list.component';
import { ListUsuariosComponent }               from './admin/seguridad/usuarios/list.component';
import { EditUsuarioComponent }                from './admin/seguridad/usuarios/edit.component';

const appRoutes: Routes = [
    // Config
    {
        path: 'admin/parametros',
        component: ListParametrosComponent,
        data: { 
            "uk": "configuracion",
            "caption": "Configuración"
        }
    },
    
    /* Catalogos */
    {
        path: 'admin/roles',
        component: ListRolesComponent,
        data: { 
            "uk": "roles",
            "caption": "Roles"
        }
    },
    {
        path: 'admin/usuarios',
        component: ListUsuariosComponent,
        data: { 
            "uk": "usuarios",
            "caption": "Usuarios"
        }
    },
    {
        path: 'admin/usuarios/agregar',
        component: EditUsuarioComponent,
        data: { 
            "uk": "usuarios",
            "caption": "Usuarios"
        }
    },
    {
        path: 'admin/usuarios/edit/:id',
        component: EditUsuarioComponent,
        data: { 
            "uk": "usuarios",
            "caption": "Usuarios"
        }
    },

    // Home
    {
        path: '',
        component: HomeComponent,
        data: {
            "uk": "home",
            "caption": "Inicio"
        }
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(
            appRoutes,
            {
                useHash: true,
                // debugging purposes only
                enableTracing: false
            }
        )
    ],
    exports: [
        RouterModule
    ],
    providers: [
    ]
})
export class AppRoutingModule { }
