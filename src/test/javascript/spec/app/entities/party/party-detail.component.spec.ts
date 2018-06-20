/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SkeletonTestModule } from '../../../test.module';
import { PartyDetailComponent } from '../../../../../../main/webapp/app/entities/party/party-detail.component';
import { PartyService } from '../../../../../../main/webapp/app/entities/party/party.service';
import { Party } from '../../../../../../main/webapp/app/entities/party/party.model';

describe('Component Tests', () => {

    describe('Party Management Detail Component', () => {
        let comp: PartyDetailComponent;
        let fixture: ComponentFixture<PartyDetailComponent>;
        let service: PartyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [PartyDetailComponent],
                providers: [
                    PartyService
                ]
            })
            .overrideTemplate(PartyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PartyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PartyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Party(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.party).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
