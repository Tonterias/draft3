/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SkeletonTestModule } from '../../../test.module';
import { UrllinkDetailComponent } from '../../../../../../main/webapp/app/entities/urllink/urllink-detail.component';
import { UrllinkService } from '../../../../../../main/webapp/app/entities/urllink/urllink.service';
import { Urllink } from '../../../../../../main/webapp/app/entities/urllink/urllink.model';

describe('Component Tests', () => {

    describe('Urllink Management Detail Component', () => {
        let comp: UrllinkDetailComponent;
        let fixture: ComponentFixture<UrllinkDetailComponent>;
        let service: UrllinkService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [UrllinkDetailComponent],
                providers: [
                    UrllinkService
                ]
            })
            .overrideTemplate(UrllinkDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UrllinkDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UrllinkService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Urllink(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.urllink).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
