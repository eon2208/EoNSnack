import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Cuisines} from '../common/cuisines';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CuisinesService {

  private baseUrl = 'http://localhost:8080/api/cuisines';

  constructor(private httpClient: HttpClient) { }

  getCuisinesListPaginate(thePage: number, thePageSize: number): Observable<GetResponseCuisines> {

    const url = `${this.baseUrl}?page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseCuisines>(url)
  }
}

interface GetResponseCuisines {
  _embedded: {
    cuisine: Cuisines[];
  }
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
