import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CelebComponent } from './celeb.component';
import { CelebDetailComponent } from './celeb-detail.component';
import { CelebPopupComponent } from './celeb-dialog.component';
import { CelebDeletePopupComponent } from './celeb-delete-dialog.component';

export const celebRoute: Routes = [
    {
        path: 'celeb',
        component: CelebComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'celeb/:id',
        component: CelebDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const celebPopupRoute: Routes = [
    {
        path: 'celeb-new',
        component: CelebPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'celeb/:id/edit',
        component: CelebPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'celeb/:id/delete',
        component: CelebDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'skeletonApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
