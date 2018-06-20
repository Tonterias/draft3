import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PartyComponent } from './party.component';
import { PartyDetailComponent } from './party-detail.component';
import { PartyPopupComponent } from './party-dialog.component';
import { PartyDeletePopupComponent } from './party-delete-dialog.component';

export const partyRoute: Routes = [
    {
        path: 'party',
        component: PartyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.party.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'party/:id',
        component: PartyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.party.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const partyPopupRoute: Routes = [
    {
        path: 'party-new',
        component: PartyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.party.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'party/:id/edit',
        component: PartyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.party.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'party/:id/delete',
        component: PartyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.party.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
