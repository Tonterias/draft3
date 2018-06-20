import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkeletonSharedModule } from '../../shared';
import {
    BlockeduserService,
    BlockeduserPopupService,
    BlockeduserComponent,
    BlockeduserDetailComponent,
    BlockeduserDialogComponent,
    BlockeduserPopupComponent,
    BlockeduserDeletePopupComponent,
    BlockeduserDeleteDialogComponent,
    blockeduserRoute,
    blockeduserPopupRoute,
} from './';

const ENTITY_STATES = [
    ...blockeduserRoute,
    ...blockeduserPopupRoute,
];

@NgModule({
    imports: [
        SkeletonSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BlockeduserComponent,
        BlockeduserDetailComponent,
        BlockeduserDialogComponent,
        BlockeduserDeleteDialogComponent,
        BlockeduserPopupComponent,
        BlockeduserDeletePopupComponent,
    ],
    entryComponents: [
        BlockeduserComponent,
        BlockeduserDialogComponent,
        BlockeduserPopupComponent,
        BlockeduserDeleteDialogComponent,
        BlockeduserDeletePopupComponent,
    ],
    providers: [
        BlockeduserService,
        BlockeduserPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonBlockeduserModule {}
