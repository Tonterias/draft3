import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Urllink } from './urllink.model';
import { UrllinkPopupService } from './urllink-popup.service';
import { UrllinkService } from './urllink.service';

@Component({
    selector: 'jhi-urllink-delete-dialog',
    templateUrl: './urllink-delete-dialog.component.html'
})
export class UrllinkDeleteDialogComponent {

    urllink: Urllink;

    constructor(
        private urllinkService: UrllinkService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.urllinkService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'urllinkListModification',
                content: 'Deleted an urllink'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-urllink-delete-popup',
    template: ''
})
export class UrllinkDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private urllinkPopupService: UrllinkPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.urllinkPopupService
                .open(UrllinkDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
