import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Celeb } from './celeb.model';
import { CelebPopupService } from './celeb-popup.service';
import { CelebService } from './celeb.service';
import { Party, PartyService } from '../party';

@Component({
    selector: 'jhi-celeb-dialog',
    templateUrl: './celeb-dialog.component.html'
})
export class CelebDialogComponent implements OnInit {

    celeb: Celeb;
    isSaving: boolean;

    parties: Party[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private celebService: CelebService,
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
        if (this.celeb.id !== undefined) {
            this.subscribeToSaveResponse(
                this.celebService.update(this.celeb));
        } else {
            this.subscribeToSaveResponse(
                this.celebService.create(this.celeb));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Celeb>>) {
        result.subscribe((res: HttpResponse<Celeb>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Celeb) {
        this.eventManager.broadcast({ name: 'celebListModification', content: 'OK'});
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

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-celeb-popup',
    template: ''
})
export class CelebPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private celebPopupService: CelebPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.celebPopupService
                    .open(CelebDialogComponent as Component, params['id']);
            } else {
                this.celebPopupService
                    .open(CelebDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
