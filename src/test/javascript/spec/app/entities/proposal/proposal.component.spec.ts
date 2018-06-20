/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SkeletonTestModule } from '../../../test.module';
import { ProposalComponent } from '../../../../../../main/webapp/app/entities/proposal/proposal.component';
import { ProposalService } from '../../../../../../main/webapp/app/entities/proposal/proposal.service';
import { Proposal } from '../../../../../../main/webapp/app/entities/proposal/proposal.model';

describe('Component Tests', () => {

    describe('Proposal Management Component', () => {
        let comp: ProposalComponent;
        let fixture: ComponentFixture<ProposalComponent>;
        let service: ProposalService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [ProposalComponent],
                providers: [
                    ProposalService
                ]
            })
            .overrideTemplate(ProposalComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProposalComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProposalService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Proposal(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.proposals[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
