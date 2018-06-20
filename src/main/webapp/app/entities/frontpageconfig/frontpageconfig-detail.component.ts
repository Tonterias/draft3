import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Frontpageconfig } from './frontpageconfig.model';
import { FrontpageconfigService } from './frontpageconfig.service';

@Component({
    selector: 'jhi-frontpageconfig-detail',
    templateUrl: './frontpageconfig-detail.component.html'
})
export class FrontpageconfigDetailComponent implements OnInit, OnDestroy {

    frontpageconfig: Frontpageconfig;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private frontpageconfigService: FrontpageconfigService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFrontpageconfigs();
    }

    load(id) {
        this.frontpageconfigService.find(id)
            .subscribe((frontpageconfigResponse: HttpResponse<Frontpageconfig>) => {
                this.frontpageconfig = frontpageconfigResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFrontpageconfigs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'frontpageconfigListModification',
            (response) => this.load(this.frontpageconfig.id)
        );
    }
}
