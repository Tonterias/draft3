import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Party } from './party.model';
import { PartyPopupService } from './party-popup.service';
import { PartyService } from './party.service';
import { User, UserService } from '../../shared';
import { Address, AddressService } from '../address';
import { Album, AlbumService } from '../album';
import { Interest, InterestService } from '../interest';
import { Activity, ActivityService } from '../activity';
import { Celeb, CelebService } from '../celeb';

@Component({
    selector: 'jhi-party-dialog',
    templateUrl: './party-dialog.component.html'
})
export class PartyDialogComponent implements OnInit {

    party: Party;
    isSaving: boolean;

    users: User[];

    addresses: Address[];

    albums: Album[];

    interests: Interest[];

    activities: Activity[];

    celebs: Celeb[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private partyService: PartyService,
        private userService: UserService,
        private addressService: AddressService,
        private albumService: AlbumService,
        private interestService: InterestService,
        private activityService: ActivityService,
        private celebService: CelebService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: HttpResponse<User[]>) => { this.users = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.addressService.query()
            .subscribe((res: HttpResponse<Address[]>) => { this.addresses = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.albumService.query()
            .subscribe((res: HttpResponse<Album[]>) => { this.albums = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.interestService.query()
            .subscribe((res: HttpResponse<Interest[]>) => { this.interests = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.activityService.query()
            .subscribe((res: HttpResponse<Activity[]>) => { this.activities = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.celebService.query()
            .subscribe((res: HttpResponse<Celeb[]>) => { this.celebs = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.party, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.party.id !== undefined) {
            this.subscribeToSaveResponse(
                this.partyService.update(this.party));
        } else {
            this.subscribeToSaveResponse(
                this.partyService.create(this.party));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Party>>) {
        result.subscribe((res: HttpResponse<Party>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Party) {
        this.eventManager.broadcast({ name: 'partyListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackAddressById(index: number, item: Address) {
        return item.id;
    }

    trackAlbumById(index: number, item: Album) {
        return item.id;
    }

    trackInterestById(index: number, item: Interest) {
        return item.id;
    }

    trackActivityById(index: number, item: Activity) {
        return item.id;
    }

    trackCelebById(index: number, item: Celeb) {
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
    selector: 'jhi-party-popup',
    template: ''
})
export class PartyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private partyPopupService: PartyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.partyPopupService
                    .open(PartyDialogComponent as Component, params['id']);
            } else {
                this.partyPopupService
                    .open(PartyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
