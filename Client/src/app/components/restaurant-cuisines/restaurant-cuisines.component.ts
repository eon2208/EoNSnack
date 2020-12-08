import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormlyFieldCheckbox } from '@ngx-formly/bootstrap';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { Cuisines } from '../../common/cuisines';
import { CuisinesService } from '../../services/cuisines.service';

@Component({
  selector: 'app-restaurant-cuisines',
  templateUrl: './restaurant-cuisines.component.html',
  styleUrls: ['./restaurant-cuisines.component.css']
})
export class RestaurantCuisinesComponent implements OnInit {

  cuisines: Cuisines[];
  isChecked: boolean = true;

  thePage: number = 1;
  thePageSize: number = 50;
  theTotalElements: number = 0;

  form = new FormGroup({});
  options: FormlyFormOptions = {};
  model = {};
  fields: FormlyFieldConfig[] = [
    {
      key: 'terms',
      type: 'multicheckbox',
      templateOptions: {
      label: 'Filter',
      options: [
        { label: 'Iron Man', value: 'iron_man' },
        { label: 'Captain America', value: 'captain_america' },
        { label: 'Black Widow', value: 'black_widow' },
        { label: 'Hulk', value: 'hulk' },
        { label: 'Captain Marvel', value: 'captain_marvel' },
      ],
    }
  }
]

  constructor(private cuisinesService: CuisinesService,
    private restaurantService: RestaurantService,
    private http: HttpClient) { }

  ngOnInit(): void {
    this.listRestaurantCuisines();
  }

  onSubmit() {
		if (this.form.valid) {
      this.http.post('url', this.model, null).subscribe((response) => {
        console.log('response:', response)
      }, (error) => {
        console.error('error:', error)
      })
    }
  }

  listRestaurantCuisines(): void {

    this.cuisinesService.getCuisinesListPaginate(this.thePage - 1, this.thePageSize).subscribe(
      this.processResult()
    );
  }

  changeFilterSelection(theEvent: number): void {
    console.log(theEvent);
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
