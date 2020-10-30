import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tags } from '../common/tags';

@Injectable({
  providedIn: 'root'
})
export class TagsService {

  baseUrl = 'http://localhost:8080/api/tags';

  constructor(private httpClient: HttpClient) { }

  getTags(): Observable<Tags[]> {

    return this.httpClient.get<GetResponseTags>(this.baseUrl).pipe(
      map(response => response._embedded.tags)
    );
  }
}

interface GetResponseTags{
  _embedded: {
    tags: Tags[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
