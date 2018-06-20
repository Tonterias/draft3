import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Frontpageconfig } from './frontpageconfig.model';
import { FrontpageconfigService } from './frontpageconfig.service';

@Injectable()
export class FrontpageconfigPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private frontpageconfigService: FrontpageconfigService

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
                this.frontpageconfigService.find(id)
                    .subscribe((frontpageconfigResponse: HttpResponse<Frontpageconfig>) => {
                        const frontpageconfig: Frontpageconfig = frontpageconfigResponse.body;
                        frontpageconfig.creationDate = this.datePipe
                            .transform(frontpageconfig.creationDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.frontpageconfigModalRef(component, frontpageconfig);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.frontpageconfigModalRef(component, new Frontpageconfig());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    frontpageconfigModalRef(component: Component, frontpageconfig: Frontpageconfig): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.frontpageconfig = frontpageconfig;
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
