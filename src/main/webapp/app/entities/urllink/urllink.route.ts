import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UrllinkComponent } from './urllink.component';
import { UrllinkDetailComponent } from './urllink-detail.component';
import { UrllinkPopupComponent } from './urllink-dialog.component';
import { UrllinkDeletePopupComponent } from './urllink-delete-dialog.component';

export const urllinkRoute: Routes = [
    {
        path: 'urllink',
        component: UrllinkComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'urllink/:id',
        component: UrllinkDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const urllinkPopupRoute: Routes = [
    {
        path: 'urllink-new',
        component: UrllinkPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'urllink/:id/edit',
        component: UrllinkPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'urllink/:id/delete',
        component: UrllinkDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
