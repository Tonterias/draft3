import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Party } from './party.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Party>;

@Injectable()
export class PartyService {

    private resourceUrl =  SERVER_API_URL + 'api/parties';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/parties';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(party: Party): Observable<EntityResponseType> {
        const copy = this.convert(party);
        return this.http.post<Party>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(party: Party): Observable<EntityResponseType> {
        const copy = this.convert(party);
        return this.http.put<Party>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Party>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Party[]>> {
        const options = createRequestOption(req);
        return this.http.get<Party[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Party[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Party[]>> {
        const options = createRequestOption(req);
        return this.http.get<Party[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Party[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Party = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Party[]>): HttpResponse<Party[]> {
        const jsonResponse: Party[] = res.body;
        const body: Party[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Party.
     */
    private convertItemFromServer(party: Party): Party {
        const copy: Party = Object.assign({}, party);
        copy.creationDate = this.dateUtils
            .convertDateTimeFromServer(party.creationDate);
        return copy;
    }

    /**
     * Convert a Party to a JSON which can be sent to the server.
     */
    private convert(party: Party): Party {
        const copy: Party = Object.assign({}, party);

        copy.creationDate = this.dateUtils.toDate(party.creationDate);
        return copy;
    }
}
