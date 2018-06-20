import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Urllink } from './urllink.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Urllink>;

@Injectable()
export class UrllinkService {

    private resourceUrl =  SERVER_API_URL + 'api/urllinks';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/urllinks';

    constructor(private http: HttpClient) { }

    create(urllink: Urllink): Observable<EntityResponseType> {
        const copy = this.convert(urllink);
        return this.http.post<Urllink>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(urllink: Urllink): Observable<EntityResponseType> {
        const copy = this.convert(urllink);
        return this.http.put<Urllink>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Urllink>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Urllink[]>> {
        const options = createRequestOption(req);
        return this.http.get<Urllink[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Urllink[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Urllink[]>> {
        const options = createRequestOption(req);
        return this.http.get<Urllink[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Urllink[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Urllink = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Urllink[]>): HttpResponse<Urllink[]> {
        const jsonResponse: Urllink[] = res.body;
        const body: Urllink[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Urllink.
     */
    private convertItemFromServer(urllink: Urllink): Urllink {
        const copy: Urllink = Object.assign({}, urllink);
        return copy;
    }

    /**
     * Convert a Urllink to a JSON which can be sent to the server.
     */
    private convert(urllink: Urllink): Urllink {
        const copy: Urllink = Object.assign({}, urllink);
        return copy;
    }
}
