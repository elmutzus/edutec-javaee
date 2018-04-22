import { NgModule } from '@angular/core';

import {
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule, 
    MatRadioModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatSidenavModule,
    MatOptionModule,
    MatSelectModule,
    MatSnackBarModule,
    MatPaginatorModule
} from '@angular/material';

@NgModule({
    imports: [
        MatButtonModule,
        MatMenuModule,
        MatToolbarModule,
        MatIconModule,
        MatCardModule,
        MatInputModule,
        MatCheckboxModule,
        MatDatepickerModule,
        MatNativeDateModule, 
        MatRadioModule,
        MatExpansionModule,
        MatFormFieldModule,
        MatSidenavModule,
        MatOptionModule,
        MatSelectModule,
        MatSnackBarModule,
        MatPaginatorModule
    ],
    exports: [
        MatButtonModule,
        MatMenuModule,
        MatToolbarModule,
        MatIconModule,
        MatCardModule,
        MatCheckboxModule,
        MatDatepickerModule,
        MatNativeDateModule, 
        MatRadioModule,
        MatExpansionModule,
        MatFormFieldModule,
        MatInputModule,
        MatSidenavModule,
        MatOptionModule,
        MatSelectModule,
        MatSnackBarModule,
        MatPaginatorModule
    ]
})
export class MaterialModule {}