/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SkeletonTestModule } from '../../../test.module';
import { BlockeduserComponent } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.component';
import { BlockeduserService } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.service';
import { Blockeduser } from '../../../../../../main/webapp/app/entities/blockeduser/blockeduser.model';

describe('Component Tests', () => {

    describe('Blockeduser Management Component', () => {
        let comp: BlockeduserComponent;
        let fixture: ComponentFixture<BlockeduserComponent>;
        let service: BlockeduserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SkeletonTestModule],
                declarations: [BlockeduserComponent],
                providers: [
                    BlockeduserService
                ]
            })
            .overrideTemplate(BlockeduserComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BlockeduserComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlockeduserService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Blockeduser(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.blockedusers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
