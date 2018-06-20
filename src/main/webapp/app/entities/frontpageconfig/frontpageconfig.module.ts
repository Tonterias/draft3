import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkeletonSharedModule } from '../../shared';
import {
    FrontpageconfigService,
    FrontpageconfigPopupService,
    FrontpageconfigComponent,
    FrontpageconfigDetailComponent,
    FrontpageconfigDialogComponent,
    FrontpageconfigPopupComponent,
    FrontpageconfigDeletePopupComponent,
    FrontpageconfigDeleteDialogComponent,
    frontpageconfigRoute,
    frontpageconfigPopupRoute,
} from './';

const ENTITY_STATES = [
    ...frontpageconfigRoute,
    ...frontpageconfigPopupRoute,
];

@NgModule({
    imports: [
        SkeletonSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FrontpageconfigComponent,
        FrontpageconfigDetailComponent,
        FrontpageconfigDialogComponent,
        FrontpageconfigDeleteDialogComponent,
        FrontpageconfigPopupComponent,
        FrontpageconfigDeletePopupComponent,
    ],
    entryComponents: [
        FrontpageconfigComponent,
        FrontpageconfigDialogComponent,
        FrontpageconfigPopupComponent,
        FrontpageconfigDeleteDialogComponent,
        FrontpageconfigDeletePopupComponent,
    ],
    providers: [
        FrontpageconfigService,
        FrontpageconfigPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonFrontpageconfigModule {}
