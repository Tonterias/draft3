import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Urllink } from './urllink.model';
import { UrllinkPopupService } from './urllink-popup.service';
import { UrllinkService } from './urllink.service';
import { Frontpageconfig, FrontpageconfigService } from '../frontpageconfig';

@Component({
    selector: 'jhi-urllink-dialog',
    templateUrl: './urllink-dialog.component.html'
})
export class UrllinkDialogComponent implements OnInit {

    urllink: Urllink;
    isSaving: boolean;

    frontpageconfigs: Frontpageconfig[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private urllinkService: UrllinkService,
        private frontpageconfigService: FrontpageconfigService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.frontpageconfigService.query()
            .subscribe((res: HttpResponse<Frontpageconfig[]>) => { this.frontpageconfigs = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.urllink.id !== undefined) {
            this.subscribeToSaveResponse(
                this.urllinkService.update(this.urllink));
        } else {
            this.subscribeToSaveResponse(
                this.urllinkService.create(this.urllink));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Urllink>>) {
        result.subscribe((res: HttpResponse<Urllink>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Urllink) {
        this.eventManager.broadcast({ name: 'urllinkListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackFrontpageconfigById(index: number, item: Frontpageconfig) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-urllink-popup',
    template: ''
})
export class UrllinkPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private urllinkPopupService: UrllinkPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.urllinkPopupService
                    .open(UrllinkDialogComponent as Component, params['id']);
            } else {
                this.urllinkPopupService
                    .open(UrllinkDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
