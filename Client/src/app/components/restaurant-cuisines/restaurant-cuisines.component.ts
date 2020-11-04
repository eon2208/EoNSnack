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

  constructor(private cuisinesService: CuisinesService) { }

  ngOnInit(): void {
    this.listRestaurantCuisines();
  }

  private listRestaurantCuisines(): void {

    this.cuisinesService.getCuisines().subscribe(
      data => {
        console.log('Cuisines = ' + JSON.stringify(data));
        this.cuisines = data;
      }
    );
  }

}
