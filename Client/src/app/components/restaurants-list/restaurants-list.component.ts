import { Component, OnInit } from '@angular/core';
import {Restaurant} from '../../common/restaurant';
import {RestaurantService} from '../../services/restaurant.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-restaurants-list',
  templateUrl: './restaurants-list.component.html',
  styleUrls: ['./restaurants-list.component.css']
})
export class RestaurantsListComponent implements OnInit {

  restaurants: Restaurant[] = [];

  thePageNumber:number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  previousKeyword: string = null;

  constructor(private restaurantService: RestaurantService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listRestaurants();
    });
  }

  private listRestaurants() {

    this.handleListRestaurants();
  }

  private handleListRestaurants() {

        this.restaurantService.getRestaurantsListPaginate(this.thePageNumber - 1, this.thePageSize).subscribe(
          this.processResult()
        );
    }
  

    private processResult() {
      return data => {
        this.restaurants = data._embedded.restaurants;
        this.thePageNumber = data.page.number + 1;
        this.thePageSize = data.page.size;
        this.theTotalElements = data.page.totalElements;
      };
}
}
