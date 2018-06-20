/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SkeletonTestModule } from '../../../test.module';
import { BlockeduserDialogComponent } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser-dialog.component';
import { BlockeduserService } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.service';
import { Blockeduser } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.model';
import { PartyService } from '../../../../../../main/webapp/app/entities/party';

describe('Component Tests', () => {

    describe('Blockeduser Management Dialog Component', () => {
        let comp: BlockeduserDialogComponent;
        let fixture: ComponentFixture<BlockeduserDialogComponent>;
        let service: BlockeduserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [BlockeduserDialogComponent],
                providers: [
                    PartyService,
                    BlockeduserService
                ]
            })
            .overrideTemplate(BlockeduserDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BlockeduserDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlockeduserService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Blockeduser(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.blockeduser = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'blockeduserListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Blockeduser();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.blockeduser = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'blockeduserListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
