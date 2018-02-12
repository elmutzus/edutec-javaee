import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ListParametrosComponent } from './shared/parametros/list.component';
import { SelectivePreloadingStrategy } from './selective-preloading-strategy';
import { HomeComponent } from './home/component';

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
