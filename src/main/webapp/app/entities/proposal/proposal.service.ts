import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Proposal } from './proposal.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Proposal>;

@Injectable()
export class ProposalService {

    private resourceUrl =  SERVER_API_URL + 'api/proposals';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/proposals';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(proposal: Proposal): Observable<EntityResponseType> {
        const copy = this.convert(proposal);
        return this.http.post<Proposal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(proposal: Proposal): Observable<EntityResponseType> {
        const copy = this.convert(proposal);
        return this.http.put<Proposal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Proposal>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Proposal[]>> {
        const options = createRequestOption(req);
        return this.http.get<Proposal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Proposal[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Proposal[]>> {
        const options = createRequestOption(req);
        return this.http.get<Proposal[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Proposal[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Proposal = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Proposal[]>): HttpResponse<Proposal[]> {
        const jsonResponse: Proposal[] = res.body;
        const body: Proposal[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Proposal.
     */
    private convertItemFromServer(proposal: Proposal): Proposal {
        const copy: Proposal = Object.assign({}, proposal);
        copy.creationDate = this.dateUtils
            .convertDateTimeFromServer(proposal.creationDate);
        copy.releaseDate = this.dateUtils
            .convertDateTimeFromServer(proposal.releaseDate);
        return copy;
    }

    /**
     * Convert a Proposal to a JSON which can be sent to the server.
     */
    private convert(proposal: Proposal): Proposal {
        const copy: Proposal = Object.assign({}, proposal);

        copy.creationDate = this.dateUtils.toDate(proposal.creationDate);

        copy.releaseDate = this.dateUtils.toDate(proposal.releaseDate);
        return copy;
    }
}
