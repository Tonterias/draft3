import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Celeb } from './celeb.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Celeb>;

@Injectable()
export class CelebService {

    private resourceUrl =  SERVER_API_URL + 'api/celebs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/celebs';

    constructor(private http: HttpClient) { }

    create(celeb: Celeb): Observable<EntityResponseType> {
        const copy = this.convert(celeb);
        return this.http.post<Celeb>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(celeb: Celeb): Observable<EntityResponseType> {
        const copy = this.convert(celeb);
        return this.http.put<Celeb>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Celeb>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Celeb[]>> {
        const options = createRequestOption(req);
        return this.http.get<Celeb[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Celeb[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Celeb[]>> {
        const options = createRequestOption(req);
        return this.http.get<Celeb[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Celeb[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Celeb = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Celeb[]>): HttpResponse<Celeb[]> {
        const jsonResponse: Celeb[] = res.body;
        const body: Celeb[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Celeb.
     */
    private convertItemFromServer(celeb: Celeb): Celeb {
        const copy: Celeb = Object.assign({}, celeb);
        return copy;
    }

    /**
     * Convert a Celeb to a JSON which can be sent to the server.
     */
    private convert(celeb: Celeb): Celeb {
        const copy: Celeb = Object.assign({}, celeb);
        return copy;
    }
}
