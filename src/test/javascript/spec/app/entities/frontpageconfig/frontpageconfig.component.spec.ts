/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SkeletonTestModule } from '../../../test.module';
import { FrontpageconfigComponent } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.component';
import { FrontpageconfigService } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.service';
import { Frontpageconfig } from '../../../../../../main/webapp/app/entities/frontpageconfig/frontpageconfig.model';

describe('Component Tests', () => {

    describe('Frontpageconfig Management Component', () => {
        let comp: FrontpageconfigComponent;
        let fixture: ComponentFixture<FrontpageconfigComponent>;
        let service: FrontpageconfigService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [FrontpageconfigComponent],
                providers: [
                    FrontpageconfigService
                ]
            })
            .overrideTemplate(FrontpageconfigComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FrontpageconfigComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FrontpageconfigService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Frontpageconfig(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.frontpageconfigs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
