import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Blockeduser } from './blockeduser.model';
import { BlockeduserService } from './blockeduser.service';

@Injectable()
export class BlockeduserPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private blockeduserService: BlockeduserService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.blockeduserService.find(id)
                    .subscribe((blockeduserResponse: HttpResponse<Blockeduser>) => {
                        const blockeduser: Blockeduser = blockeduserResponse.body;
                        blockeduser.creationDate = this.datePipe
                            .transform(blockeduser.creationDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.blockeduserModalRef(component, blockeduser);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.blockeduserModalRef(component, new Blockeduser());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    blockeduserModalRef(component: Component, blockeduser: Blockeduser): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.blockeduser = blockeduser;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
