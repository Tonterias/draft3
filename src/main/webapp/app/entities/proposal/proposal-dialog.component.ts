import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Proposal } from './proposal.model';
import { ProposalPopupService } from './proposal-popup.service';
import { ProposalService } from './proposal.service';
import { Party, PartyService } from '../party';
import { Post, PostService } from '../post';

@Component({
    selector: 'jhi-proposal-dialog',
    templateUrl: './proposal-dialog.component.html'
})
export class ProposalDialogComponent implements OnInit {

    proposal: Proposal;
    isSaving: boolean;

    parties: Party[];

    posts: Post[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private proposalService: ProposalService,
        private partyService: PartyService,
        private postService: PostService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.partyService.query()
            .subscribe((res: HttpResponse<Party[]>) => { this.parties = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postService.query()
            .subscribe((res: HttpResponse<Post[]>) => { this.posts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.proposal.id !== undefined) {
            this.subscribeToSaveResponse(
                this.proposalService.update(this.proposal));
        } else {
            this.subscribeToSaveResponse(
                this.proposalService.create(this.proposal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Proposal>>) {
        result.subscribe((res: HttpResponse<Proposal>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Proposal) {
        this.eventManager.broadcast({ name: 'proposalListModification', content: 'OK'});
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

    trackPostById(index: number, item: Post) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-proposal-popup',
    template: ''
})
export class ProposalPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private proposalPopupService: ProposalPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.proposalPopupService
                    .open(ProposalDialogComponent as Component, params['id']);
            } else {
                this.proposalPopupService
                    .open(ProposalDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
