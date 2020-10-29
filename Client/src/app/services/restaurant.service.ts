import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Restaurant } from '../common/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private baseUrl = 'http://localhost:8080/api/restaurants';

  constructor(private httpClient: HttpClient) { }

  getRestaurantsListPaginate(thePage: number, thePageSize: number, theCategoryId: number): Observable<GetResponseRestaurant> {
    const url = `${this.baseUrl}`
  }

}

interface GetResponseRestaurant {
  _embedded: {
    restaurants: Restaurant[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
