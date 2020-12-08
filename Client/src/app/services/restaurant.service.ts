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
  private filterCuisinesUrl = 'http://localhost:8080/api/restaurants/filter/cuisines?cuisinesId='

  constructor(private httpClient: HttpClient) { }

  private getRestaurants(searchUrl: string){
    
    return this.httpClient.get<GetResponseRestaurants>(searchUrl).pipe(
      map(response => response._embedded.restaurants)
    )
  }

  getRestaurantsListPaginate(thePage: number, thePageSize: number): Observable<GetResponseRestaurants> {
    const url = `${this.baseUrl}?page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseRestaurants>(url)
  }

  getRestaurantsListByCuisines(theCuisineId: number): Observable<GetResponseRestaurants> {

    const url = `${this.cuisinesUrl}/${theCuisineId}/restaurant`;

    return this.httpClient.get<GetResponseRestaurants>(url);
  }

  getFilteredRestaurantsList(filterId: number[]): Observable<GetResponseRestaurants> {
    
    let url = this.filterCuisinesUrl;

    for (let cuisineId of filterId){
      url += cuisineId.toString + ',';
    }

    return this.httpClient.get<GetResponseRestaurants>(url);
  }

}

interface GetResponseRestaurants {
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
