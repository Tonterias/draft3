import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Celeb } from './celeb.model';
import { CelebService } from './celeb.service';

@Component({
    selector: 'jhi-celeb-detail',
    templateUrl: './celeb-detail.component.html'
})
export class CelebDetailComponent implements OnInit, OnDestroy {

    celeb: Celeb;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private celebService: CelebService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCelebs();
    }

    load(id) {
        this.celebService.find(id)
            .subscribe((celebResponse: HttpResponse<Celeb>) => {
                this.celeb = celebResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCelebs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'celebListModification',
            (response) => this.load(this.celeb.id)
        );
    }
}
