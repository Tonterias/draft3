import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Urllink } from './urllink.model';
import { UrllinkService } from './urllink.service';

@Component({
    selector: 'jhi-urllink-detail',
    templateUrl: './urllink-detail.component.html'
})
export class UrllinkDetailComponent implements OnInit, OnDestroy {

    urllink: Urllink;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private urllinkService: UrllinkService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUrllinks();
    }

    load(id) {
        this.urllinkService.find(id)
            .subscribe((urllinkResponse: HttpResponse<Urllink>) => {
                this.urllink = urllinkResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUrllinks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'urllinkListModification',
            (response) => this.load(this.urllink.id)
        );
    }
}
