import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Celeb } from './celeb.model';
import { CelebPopupService } from './celeb-popup.service';
import { CelebService } from './celeb.service';

@Component({
    selector: 'jhi-celeb-delete-dialog',
    templateUrl: './celeb-delete-dialog.component.html'
})
export class CelebDeleteDialogComponent {

    celeb: Celeb;

    constructor(
        private celebService: CelebService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.celebService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'celebListModification',
                content: 'Deleted an celeb'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-celeb-delete-popup',
    template: ''
})
export class CelebDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private celebPopupService: CelebPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.celebPopupService
                .open(CelebDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
