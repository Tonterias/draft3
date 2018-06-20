import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { FrontpageconfigComponent } from './frontpageconfig.component';
import { FrontpageconfigDetailComponent } from './frontpageconfig-detail.component';
import { FrontpageconfigPopupComponent } from './frontpageconfig-dialog.component';
import { FrontpageconfigDeletePopupComponent } from './frontpageconfig-delete-dialog.component';

export const frontpageconfigRoute: Routes = [
    {
        path: 'frontpageconfig',
        component: FrontpageconfigComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.frontpageconfig.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'frontpageconfig/:id',
        component: FrontpageconfigDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.frontpageconfig.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const frontpageconfigPopupRoute: Routes = [
    {
        path: 'frontpageconfig-new',
        component: FrontpageconfigPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.frontpageconfig.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'frontpageconfig/:id/edit',
        component: FrontpageconfigPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.frontpageconfig.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'frontpageconfig/:id/delete',
        component: FrontpageconfigDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.frontpageconfig.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
