import { Component, OnInit } from '@angular/core';
import {Cuisines} from '../../common/cuisines';
import {CuisinesService} from '../../services/cuisines.service';

@Component({
  selector: 'app-restaurant-cuisines',
  templateUrl: './restaurant-cuisines.component.html',
  styleUrls: ['./restaurant-cuisines.component.css']
})
export class RestaurantCuisinesComponent implements OnInit {

  cuisines: Cuisines[];
  isChecked: boolean = true;

  thePage:number = 1;
  thePageSize: number = 10;
  theTotalElements: number = 0;

  constructor(private cuisinesService: CuisinesService) { }

  ngOnInit(): void {
    this.listRestaurantCuisines();
  }

  listRestaurantCuisines(): void {

    this.cuisinesService.getCuisinesListPaginate(this.thePage - 1, this.thePageSize).subscribe(
      this.processResult()
    );
  }

  private processResult() {
    return data => {
      this.cuisines = data._embedded.cuisine;
      this.thePage = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalElements;
    };
  }
}
