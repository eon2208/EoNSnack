import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Restaurant } from '../common/restaurant';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private baseUrl = 'http://localhost:8080/api/restaurants';
  private cuisinesUrl = 'http://localhost:8080/api/cuisines';

  constructor(private httpClient: HttpClient) { }

  private getRestaurants(searchUrl: string){
    
    return this.httpClient.get<GetResponseRestaurant>(searchUrl).pipe(
      map(response => response._embedded.restaurants)
    )
  }

  getRestaurantsListPaginate(thePage: number, thePageSize: number): Observable<GetResponseRestaurant> {
    const url = `${this.baseUrl}?page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseRestaurant>(url)
  }

  getRestaurantsListByCuisines(theCuisineId: number): Observable<Restaurant[]> {

    const url = `${this.cuisinesUrl}/${theCuisineId}/restaurant`;

    return this.getRestaurants(url);
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
