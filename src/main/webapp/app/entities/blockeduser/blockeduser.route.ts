import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BlockeduserComponent } from './blockeduser.component';
import { BlockeduserDetailComponent } from './blockeduser-detail.component';
import { BlockeduserPopupComponent } from './blockeduser-dialog.component';
import { BlockeduserDeletePopupComponent } from './blockeduser-delete-dialog.component';

export const blockeduserRoute: Routes = [
    {
        path: 'blockeduser',
        component: BlockeduserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.blockeduser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'blockeduser/:id',
        component: BlockeduserDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.blockeduser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const blockeduserPopupRoute: Routes = [
    {
        path: 'blockeduser-new',
        component: BlockeduserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.blockeduser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'blockeduser/:id/edit',
        component: BlockeduserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.blockeduser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'blockeduser/:id/delete',
        component: BlockeduserDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.blockeduser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
