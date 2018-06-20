import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SkeletonSharedModule } from '../../shared';
import {
    ProposalService,
    ProposalPopupService,
    ProposalComponent,
    ProposalDetailComponent,
    ProposalDialogComponent,
    ProposalPopupComponent,
    ProposalDeletePopupComponent,
    ProposalDeleteDialogComponent,
    proposalRoute,
    proposalPopupRoute,
} from './';

const ENTITY_STATES = [
    ...proposalRoute,
    ...proposalPopupRoute,
];

@NgModule({
    imports: [
        SkeletonSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProposalComponent,
        ProposalDetailComponent,
        ProposalDialogComponent,
        ProposalDeleteDialogComponent,
        ProposalPopupComponent,
        ProposalDeletePopupComponent,
    ],
    entryComponents: [
        ProposalComponent,
        ProposalDialogComponent,
        ProposalPopupComponent,
        ProposalDeleteDialogComponent,
        ProposalDeletePopupComponent,
    ],
    providers: [
        ProposalService,
        ProposalPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SkeletonProposalModule {}
