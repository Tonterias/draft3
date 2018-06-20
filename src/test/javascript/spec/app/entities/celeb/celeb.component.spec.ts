/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SkeletonTestModule } from '../../../test.module';
import { CelebComponent } from '../../../../../../main/webapp/app/entities/celeb/celeb.component';
import { CelebService } from '../../../../../../main/webapp/app/entities/celeb/celeb.service';
import { Celeb } from '../../../../../../main/webapp/app/entities/celeb/celeb.model';

describe('Component Tests', () => {

    describe('Celeb Management Component', () => {
        let comp: CelebComponent;
        let fixture: ComponentFixture<CelebComponent>;
        let service: CelebService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [CelebComponent],
                providers: [
                    CelebService
                ]
            })
            .overrideTemplate(CelebComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CelebComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CelebService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Celeb(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.celebs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
