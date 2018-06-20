import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Proposal } from './proposal.model';
import { ProposalService } from './proposal.service';

@Injectable()
export class ProposalPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private proposalService: ProposalService

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
                this.proposalService.find(id)
                    .subscribe((proposalResponse: HttpResponse<Proposal>) => {
                        const proposal: Proposal = proposalResponse.body;
                        proposal.creationDate = this.datePipe
                            .transform(proposal.creationDate, 'yyyy-MM-ddTHH:mm:ss');
                        proposal.releaseDate = this.datePipe
                            .transform(proposal.releaseDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.proposalModalRef(component, proposal);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.proposalModalRef(component, new Proposal());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    proposalModalRef(component: Component, proposal: Proposal): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.proposal = proposal;
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
