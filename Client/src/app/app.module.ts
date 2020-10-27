import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestaurantsListComponent } from './components/restaurants-list/restaurants-list.component';
import { MealsListComponent } from './components/meals-list/meals-list.component';
import { SearchComponent } from './components/search/search.component';
import { RestaurantCuisinesComponent } from './components/restaurant-cuisines/restaurant-cuisines.component';
import { RestaurantMealsComponent } from './components/restaurant-meals/restaurant-meals.component';

@NgModule({
  declarations: [
    AppComponent,
    RestaurantsListComponent,
    MealsListComponent,
    SearchComponent,
    RestaurantCuisinesComponent,
    RestaurantMealsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
