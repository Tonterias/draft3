import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Blockeduser } from './blockeduser.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Blockeduser>;

@Injectable()
export class BlockeduserService {

    private resourceUrl =  SERVER_API_URL + 'api/blockedusers';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/blockedusers';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(blockeduser: Blockeduser): Observable<EntityResponseType> {
        const copy = this.convert(blockeduser);
        return this.http.post<Blockeduser>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(blockeduser: Blockeduser): Observable<EntityResponseType> {
        const copy = this.convert(blockeduser);
        return this.http.put<Blockeduser>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Blockeduser>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Blockeduser[]>> {
        const options = createRequestOption(req);
        return this.http.get<Blockeduser[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Blockeduser[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Blockeduser[]>> {
        const options = createRequestOption(req);
        return this.http.get<Blockeduser[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Blockeduser[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Blockeduser = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Blockeduser[]>): HttpResponse<Blockeduser[]> {
        const jsonResponse: Blockeduser[] = res.body;
        const body: Blockeduser[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Blockeduser.
     */
    private convertItemFromServer(blockeduser: Blockeduser): Blockeduser {
        const copy: Blockeduser = Object.assign({}, blockeduser);
        copy.creationDate = this.dateUtils
            .convertDateTimeFromServer(blockeduser.creationDate);
        return copy;
    }

    /**
     * Convert a Blockeduser to a JSON which can be sent to the server.
     */
    private convert(blockeduser: Blockeduser): Blockeduser {
        const copy: Blockeduser = Object.assign({}, blockeduser);

        copy.creationDate = this.dateUtils.toDate(blockeduser.creationDate);
        return copy;
    }
}
