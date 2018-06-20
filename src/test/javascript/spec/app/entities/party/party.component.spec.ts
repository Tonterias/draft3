/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SkeletonTestModule } from '../../../test.module';
import { PartyComponent } from '../../../../../../main/webapp/app/entities/party/party.component';
import { PartyService } from '../../../../../../main/webapp/app/entities/party/party.service';
import { Party } from '../../../../../../main/webapp/app/entities/party/party.model';

describe('Component Tests', () => {

    describe('Party Management Component', () => {
        let comp: PartyComponent;
        let fixture: ComponentFixture<PartyComponent>;
        let service: PartyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [PartyComponent],
                providers: [
                    PartyService
                ]
            })
            .overrideTemplate(PartyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PartyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PartyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Party(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.parties[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
