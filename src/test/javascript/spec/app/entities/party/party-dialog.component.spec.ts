/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SkeletonTestModule } from '../../../test.module';
import { PartyDialogComponent } from '../../../../../../main/webapp/app/entities/party/party-dialog.component';
import { PartyService } from '../../../../../../main/webapp/app/entities/party/party.service';
import { Party } from '../../../../../../main/webapp/app/entities/party/party.model';
import { UserService } from '../../../../../../main/webapp/app/shared';
import { AddressService } from '../../../../../../main/webapp/app/entities/address';
import { AlbumService } from '../../../../../../main/webapp/app/entities/album';
import { InterestService } from '../../../../../../main/webapp/app/entities/interest';
import { ActivityService } from '../../../../../../main/webapp/app/entities/activity';
import { CelebService } from '../../../../../../main/webapp/app/entities/celeb';

describe('Component Tests', () => {

    describe('Party Management Dialog Component', () => {
        let comp: PartyDialogComponent;
        let fixture: ComponentFixture<PartyDialogComponent>;
        let service: PartyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [PartyDialogComponent],
                providers: [
                    UserService,
                    AddressService,
                    AlbumService,
                    InterestService,
                    ActivityService,
                    CelebService,
                    PartyService
                ]
            })
            .overrideTemplate(PartyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PartyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PartyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Party(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.party = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'partyListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Party();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.party = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'partyListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
