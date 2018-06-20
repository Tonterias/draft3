import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkeletonSharedModule } from '../../shared';
import {
    UrllinkService,
    UrllinkPopupService,
    UrllinkComponent,
    UrllinkDetailComponent,
    UrllinkDialogComponent,
    UrllinkPopupComponent,
    UrllinkDeletePopupComponent,
    UrllinkDeleteDialogComponent,
    urllinkRoute,
    urllinkPopupRoute,
} from './';

const ENTITY_STATES = [
    ...urllinkRoute,
    ...urllinkPopupRoute,
];

@NgModule({
    imports: [
        SkeletonSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UrllinkComponent,
        UrllinkDetailComponent,
        UrllinkDialogComponent,
        UrllinkDeleteDialogComponent,
        UrllinkPopupComponent,
        UrllinkDeletePopupComponent,
    ],
    entryComponents: [
        UrllinkComponent,
        UrllinkDialogComponent,
        UrllinkPopupComponent,
        UrllinkDeleteDialogComponent,
        UrllinkDeletePopupComponent,
    ],
    providers: [
        UrllinkService,
        UrllinkPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonUrllinkModule {}
