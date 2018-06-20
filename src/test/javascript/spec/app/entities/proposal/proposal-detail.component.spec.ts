/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SkeletonTestModule } from '../../../test.module';
import { ProposalDetailComponent } from '../../../../../../main/webapp/app/entities/proposal/proposal-detail.component';
import { ProposalService } from '../../../../../../main/webapp/app/entities/proposal/proposal.service';
import { Proposal } from '../../../../../../main/webapp/app/entities/proposal/proposal.model';

describe('Component Tests', () => {

    describe('Proposal Management Detail Component', () => {
        let comp: ProposalDetailComponent;
        let fixture: ComponentFixture<ProposalDetailComponent>;
        let service: ProposalService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [ProposalDetailComponent],
                providers: [
                    ProposalService
                ]
            })
            .overrideTemplate(ProposalDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProposalDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProposalService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Proposal(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.proposal).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
