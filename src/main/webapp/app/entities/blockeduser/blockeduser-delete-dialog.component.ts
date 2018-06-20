import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Blockeduser } from './blockeduser.model';
import { BlockeduserPopupService } from './blockeduser-popup.service';
import { BlockeduserService } from './blockeduser.service';

@Component({
    selector: 'jhi-blockeduser-delete-dialog',
    templateUrl: './blockeduser-delete-dialog.component.html'
})
export class BlockeduserDeleteDialogComponent {

    blockeduser: Blockeduser;

    constructor(
        private blockeduserService: BlockeduserService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.blockeduserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'blockeduserListModification',
                content: 'Deleted an blockeduser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-blockeduser-delete-popup',
    template: ''
})
export class BlockeduserDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private blockeduserPopupService: BlockeduserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.blockeduserPopupService
                .open(BlockeduserDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
