import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkeletonSharedModule } from '../../shared';
import {
    CelebService,
    CelebPopupService,
    CelebComponent,
    CelebDetailComponent,
    CelebDialogComponent,
    CelebPopupComponent,
    CelebDeletePopupComponent,
    CelebDeleteDialogComponent,
    celebRoute,
    celebPopupRoute,
} from './';

const ENTITY_STATES = [
    ...celebRoute,
    ...celebPopupRoute,
];

@NgModule({
    imports: [
        SkeletonSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CelebComponent,
        CelebDetailComponent,
        CelebDialogComponent,
        CelebDeleteDialogComponent,
        CelebPopupComponent,
        CelebDeletePopupComponent,
    ],
    entryComponents: [
        CelebComponent,
        CelebDialogComponent,
        CelebPopupComponent,
        CelebDeleteDialogComponent,
        CelebDeletePopupComponent,
    ],
    providers: [
        CelebService,
        CelebPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonCelebModule {}
