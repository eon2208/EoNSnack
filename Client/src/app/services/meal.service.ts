import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Meal } from '../common/meal';

@Injectable({
  providedIn: 'root'
})
export class MealService {

  private baseUrl = 'http://localhost:8080/api/meals';

  constructor(private httpClient: HttpClient) { }

  private getMeals(searchUrl: string) {

    return this.httpClient.get<GetResponseMeals>(searchUrl).pipe(
      map(response => response._embedded.meals)
    )
  }

  private getMealsForRestaurant(theRestaurantId: number,thePage: number, thePageSize: number): Observable<Meal> {
    
    const mealUrl = `${this.baseUrl}/search/findAllByRestaurantId/${theRestaurantId}&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<Meal>(mealUrl);
  }

}

interface GetResponseMeals {
  _embedded: {
    meals: Meal[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
