/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SkeletonTestModule } from '../../../test.module';
import { UrllinkDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/urllink/urllink-delete-dialog.component';
import { UrllinkService } from '../../../../../../main/webapp/app/entities/urllink/urllink.service';

describe('Component Tests', () => {

    describe('Urllink Management Delete Component', () => {
        let comp: UrllinkDeleteDialogComponent;
        let fixture: ComponentFixture<UrllinkDeleteDialogComponent>;
        let service: UrllinkService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [UrllinkDeleteDialogComponent],
                providers: [
                    UrllinkService
                ]
            })
            .overrideTemplate(UrllinkDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UrllinkDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UrllinkService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
