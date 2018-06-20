import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkeletonSharedModule } from '../../shared';
import { SkeletonAdminModule } from '../../admin/admin.module';
import {
    PartyService,
    PartyPopupService,
    PartyComponent,
    PartyDetailComponent,
    PartyDialogComponent,
    PartyPopupComponent,
    PartyDeletePopupComponent,
    PartyDeleteDialogComponent,
    partyRoute,
    partyPopupRoute,
} from './';

const ENTITY_STATES = [
    ...partyRoute,
    ...partyPopupRoute,
];

@NgModule({
    imports: [
        SkeletonSharedModule,
        SkeletonAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PartyComponent,
        PartyDetailComponent,
        PartyDialogComponent,
        PartyDeleteDialogComponent,
        PartyPopupComponent,
        PartyDeletePopupComponent,
    ],
    entryComponents: [
        PartyComponent,
        PartyDialogComponent,
        PartyPopupComponent,
        PartyDeleteDialogComponent,
        PartyDeletePopupComponent,
    ],
    providers: [
        PartyService,
        PartyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonPartyModule {}
