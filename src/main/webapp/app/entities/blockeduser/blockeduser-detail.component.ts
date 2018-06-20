import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Blockeduser } from './blockeduser.model';
import { BlockeduserService } from './blockeduser.service';

@Component({
    selector: 'jhi-blockeduser-detail',
    templateUrl: './blockeduser-detail.component.html'
})
export class BlockeduserDetailComponent implements OnInit, OnDestroy {

    blockeduser: Blockeduser;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private blockeduserService: BlockeduserService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBlockedusers();
    }

    load(id) {
        this.blockeduserService.find(id)
            .subscribe((blockeduserResponse: HttpResponse<Blockeduser>) => {
                this.blockeduser = blockeduserResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBlockedusers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'blockeduserListModification',
            (response) => this.load(this.blockeduser.id)
        );
    }
}
