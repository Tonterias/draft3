import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Frontpageconfig } from './frontpageconfig.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Frontpageconfig>;

@Injectable()
export class FrontpageconfigService {

    private resourceUrl =  SERVER_API_URL + 'api/frontpageconfigs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/frontpageconfigs';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(frontpageconfig: Frontpageconfig): Observable<EntityResponseType> {
        const copy = this.convert(frontpageconfig);
        return this.http.post<Frontpageconfig>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(frontpageconfig: Frontpageconfig): Observable<EntityResponseType> {
        const copy = this.convert(frontpageconfig);
        return this.http.put<Frontpageconfig>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Frontpageconfig>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Frontpageconfig[]>> {
        const options = createRequestOption(req);
        return this.http.get<Frontpageconfig[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Frontpageconfig[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Frontpageconfig[]>> {
        const options = createRequestOption(req);
        return this.http.get<Frontpageconfig[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Frontpageconfig[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Frontpageconfig = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Frontpageconfig[]>): HttpResponse<Frontpageconfig[]> {
        const jsonResponse: Frontpageconfig[] = res.body;
        const body: Frontpageconfig[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Frontpageconfig.
     */
    private convertItemFromServer(frontpageconfig: Frontpageconfig): Frontpageconfig {
        const copy: Frontpageconfig = Object.assign({}, frontpageconfig);
        copy.creationDate = this.dateUtils
            .convertDateTimeFromServer(frontpageconfig.creationDate);
        return copy;
    }

    /**
     * Convert a Frontpageconfig to a JSON which can be sent to the server.
     */
    private convert(frontpageconfig: Frontpageconfig): Frontpageconfig {
        const copy: Frontpageconfig = Object.assign({}, frontpageconfig);

        copy.creationDate = this.dateUtils.toDate(frontpageconfig.creationDate);
        return copy;
    }
}
