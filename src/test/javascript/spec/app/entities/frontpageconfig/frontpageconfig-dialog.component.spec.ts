/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SkeletonTestModule } from '../../../test.module';
import { FrontpageconfigDialogComponent } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig-dialog.component';
import { FrontpageconfigService } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.service';
import { Frontpageconfig } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.model';
import { PostService } from '../../../../../../main/webapp/app/entities/post';
import { UrllinkService } from '../../../../../../main/webapp/app/entities/urllink';

describe('Component Tests', () => {

    describe('Frontpageconfig Management Dialog Component', () => {
        let comp: FrontpageconfigDialogComponent;
        let fixture: ComponentFixture<FrontpageconfigDialogComponent>;
        let service: FrontpageconfigService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [FrontpageconfigDialogComponent],
                providers: [
                    PostService,
                    UrllinkService,
                    FrontpageconfigService
                ]
            })
            .overrideTemplate(FrontpageconfigDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FrontpageconfigDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FrontpageconfigService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Frontpageconfig(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.frontpageconfig = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'frontpageconfigListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Frontpageconfig();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.frontpageconfig = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'frontpageconfigListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
