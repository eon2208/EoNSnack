import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantCuisinesComponent } from './restaurant-cuisines.component';

describe('RestaurantCuisinesComponent', () => {
  let component: RestaurantCuisinesComponent;
  let fixture: ComponentFixture<RestaurantCuisinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantCuisinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantCuisinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
