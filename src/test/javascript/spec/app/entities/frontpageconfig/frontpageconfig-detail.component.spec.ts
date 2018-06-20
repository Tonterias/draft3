/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SkeletonTestModule } from '../../../test.module';
import { FrontpageconfigDetailComponent } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig-detail.component';
import { FrontpageconfigService } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.service';
import { Frontpageconfig } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.model';

describe('Component Tests', () => {

    describe('Frontpageconfig Management Detail Component', () => {
        let comp: FrontpageconfigDetailComponent;
        let fixture: ComponentFixture<FrontpageconfigDetailComponent>;
        let service: FrontpageconfigService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [FrontpageconfigDetailComponent],
                providers: [
                    FrontpageconfigService
                ]
            })
            .overrideTemplate(FrontpageconfigDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FrontpageconfigDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FrontpageconfigService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Frontpageconfig(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.frontpageconfig).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
