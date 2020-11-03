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

  getCuisines(): Observable<Cuisines[]> {
    return this.httpClient.get<GetResponseCuisines>(this.baseUrl).pipe(
      map(response => response._embedded.cuisines)
    );
  }
}

interface GetResponseCuisines {
  _embedded: {
    cuisines: Cuisines[];
  };
}
