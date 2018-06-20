import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Frontpageconfig } from './frontpageconfig.model';
import { FrontpageconfigPopupService } from './frontpageconfig-popup.service';
import { FrontpageconfigService } from './frontpageconfig.service';

@Component({
    selector: 'jhi-frontpageconfig-delete-dialog',
    templateUrl: './frontpageconfig-delete-dialog.component.html'
})
export class FrontpageconfigDeleteDialogComponent {

    frontpageconfig: Frontpageconfig;

    constructor(
        private frontpageconfigService: FrontpageconfigService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.frontpageconfigService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'frontpageconfigListModification',
                content: 'Deleted an frontpageconfig'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-frontpageconfig-delete-popup',
    template: ''
})
export class FrontpageconfigDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private frontpageconfigPopupService: FrontpageconfigPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.frontpageconfigPopupService
                .open(FrontpageconfigDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
