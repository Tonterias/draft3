import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Party } from './party.model';
import { PartyPopupService } from './party-popup.service';
import { PartyService } from './party.service';

@Component({
    selector: 'jhi-party-delete-dialog',
    templateUrl: './party-delete-dialog.component.html'
})
export class PartyDeleteDialogComponent {

    party: Party;

    constructor(
        private partyService: PartyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.partyService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'partyListModification',
                content: 'Deleted an party'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-party-delete-popup',
    template: ''
})
export class PartyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private partyPopupService: PartyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.partyPopupService
                .open(PartyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
