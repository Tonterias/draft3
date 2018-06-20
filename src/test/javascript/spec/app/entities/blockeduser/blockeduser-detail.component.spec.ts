/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SkeletonTestModule } from '../../../test.module';
import { BlockeduserDetailComponent } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser-detail.component';
import { BlockeduserService } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.service';
import { Blockeduser } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.model';

describe('Component Tests', () => {

    describe('Blockeduser Management Detail Component', () => {
        let comp: BlockeduserDetailComponent;
        let fixture: ComponentFixture<BlockeduserDetailComponent>;
        let service: BlockeduserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [BlockeduserDetailComponent],
                providers: [
                    BlockeduserService
                ]
            })
            .overrideTemplate(BlockeduserDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BlockeduserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlockeduserService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Blockeduser(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.blockeduser).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
