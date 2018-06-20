/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SkeletonTestModule } from '../../../test.module';
import { FrontpageconfigDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig-delete-dialog.component';
import { FrontpageconfigService } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.service';

describe('Component Tests', () => {

    describe('Frontpageconfig Management Delete Component', () => {
        let comp: FrontpageconfigDeleteDialogComponent;
        let fixture: ComponentFixture<FrontpageconfigDeleteDialogComponent>;
        let service: FrontpageconfigService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [FrontpageconfigDeleteDialogComponent],
                providers: [
                    FrontpageconfigService
                ]
            })
            .overrideTemplate(FrontpageconfigDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FrontpageconfigDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FrontpageconfigService);
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
