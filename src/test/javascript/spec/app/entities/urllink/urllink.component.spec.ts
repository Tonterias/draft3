/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SkeletonTestModule } from '../../../test.module';
import { UrllinkComponent } from '../../../../../../main/webapp/app/entities/urllink/urllink.component';
import { UrllinkService } from '../../../../../../main/webapp/app/entities/urllink/urllink.service';
import { Urllink } from '../../../../../../main/webapp/app/entities/urllink/urllink.model';

describe('Component Tests', () => {

    describe('Urllink Management Component', () => {
        let comp: UrllinkComponent;
        let fixture: ComponentFixture<UrllinkComponent>;
        let service: UrllinkService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [UrllinkComponent],
                providers: [
                    UrllinkService
                ]
            })
            .overrideTemplate(UrllinkComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UrllinkComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UrllinkService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Urllink(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.urllinks[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
