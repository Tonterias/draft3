import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Blockeduser } from './blockeduser.model';
import { BlockeduserPopupService } from './blockeduser-popup.service';
import { BlockeduserService } from './blockeduser.service';
import { Party, PartyService } from '../party';

@Component({
    selector: 'jhi-blockeduser-dialog',
    templateUrl: './blockeduser-dialog.component.html'
})
export class BlockeduserDialogComponent implements OnInit {

    blockeduser: Blockeduser;
    isSaving: boolean;

    parties: Party[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private blockeduserService: BlockeduserService,
        private partyService: PartyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.partyService.query()
            .subscribe((res: HttpResponse<Party[]>) => { this.parties = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.blockeduser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.blockeduserService.update(this.blockeduser));
        } else {
            this.subscribeToSaveResponse(
                this.blockeduserService.create(this.blockeduser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Blockeduser>>) {
        result.subscribe((res: HttpResponse<Blockeduser>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Blockeduser) {
        this.eventManager.broadcast({ name: 'blockeduserListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPartyById(index: number, item: Party) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-blockeduser-popup',
    template: ''
})
export class BlockeduserPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private blockeduserPopupService: BlockeduserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.blockeduserPopupService
                    .open(BlockeduserDialogComponent as Component, params['id']);
            } else {
                this.blockeduserPopupService
                    .open(BlockeduserDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
