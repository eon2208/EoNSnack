import { Component, OnInit } from '@angular/core';
import { Meal } from '../../common/meal';
import { MealService } from '../../services/meal.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-meals-list',
  templateUrl: './meals-list.component.html',
  styleUrls: ['./meals-list.component.css']
})
export class MealsListComponent implements OnInit {

  meals: Meal[] = [];

  thePageNumber: number = 1;
  thePageSize: number = 20;
  theTotalElements: number = 0;

  constructor(private mealService: MealService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listMeals();
    })
  }

  private listMeals() {

    this.handleListMeals();
  }

  private handleListMeals() {
    this.mealService.getMealListPaginate(this.thePageNumber - 1, this.thePageSize).subscribe(
      this.processResult()
    );
  }

  private processPickResult() {
    return data => {
      this.meals = data._embedded.meals;
    }
  }

  private processResult() {
    return data => {
      this.meals = data._embedded.meals;
      this.thePageNumber = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalElements;
    };
}

}
