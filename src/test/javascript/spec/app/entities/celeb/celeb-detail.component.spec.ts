/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SkeletonTestModule } from '../../../test.module';
import { CelebDetailComponent } from '../../../../../../main/webapp/app/entities/celeb/celeb-detail.component';
import { CelebService } from '../../../../../../main/webapp/app/entities/celeb/celeb.service';
import { Celeb } from '../../../../../../main/webapp/app/entities/celeb/celeb.model';

describe('Component Tests', () => {

    describe('Celeb Management Detail Component', () => {
        let comp: CelebDetailComponent;
        let fixture: ComponentFixture<CelebDetailComponent>;
        let service: CelebService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [CelebDetailComponent],
                providers: [
                    CelebService
                ]
            })
            .overrideTemplate(CelebDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CelebDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CelebService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Celeb(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.celeb).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
