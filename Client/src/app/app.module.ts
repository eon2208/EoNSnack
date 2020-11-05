import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestaurantsListComponent } from './components/restaurants-list/restaurants-list.component';
import { MealsListComponent } from './components/meals-list/meals-list.component';
import { SearchComponent } from './components/search/search.component';
import { RestaurantCuisinesComponent } from './components/restaurant-cuisines/restaurant-cuisines.component';
import { RestaurantMealsComponent } from './components/restaurant-meals/restaurant-meals.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FormlyModule } from '@ngx-formly/core';
import { FormlyBootstrapModule } from '@ngx-formly/bootstrap';
import { FormlyMaterialModule } from '@ngx-formly/material';
import { MatCardModule } from '@angular/material/card';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatDialogModule } from '@angular/material/dialog'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatRadioModule } from '@angular/material/radio'
import { MatSelectModule } from '@angular/material/select'
import { MatButtonModule } from '@angular/material/button'
import { MatCheckboxModule } from '@angular/material/checkbox'
import { MatNativeDateModule } from '@angular/material/core'
import { FormlyMatDatepickerModule } from '@ngx-formly/material/datepicker';
import { FormlyMatToggleModule } from '@ngx-formly/material/toggle';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { from } from 'rxjs';

const routes: Routes = [
  { path: 'cuisine/:cuisineId', component: RestaurantsListComponent },
  { path: 'restaurants', component: RestaurantsListComponent },
  { path: 'cuisines', component: RestaurantsListComponent },
  { path: 'meals', component: MealsListComponent },
  { path: 'tags', component: MealsListComponent },
  { path: '', redirectTo: '/restaurants', pathMatch: 'full' },
  { path: '**', redirectTo: '/restaurants', pathMatch: 'full' }
  /*{path: 'products/:id', component: ProductDetailsComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'search/:keyword', component: ProductListComponent},
  {path: 'category/:id', component: ProductListComponent},
  {path: 'category', component: ProductListComponent},
  {path: 'products', component: ProductListComponent},
  {path: '', redirectTo: '/products', pathMatch: 'full'},
  {path: '**', redirectTo: '/products', pathMatch: 'full'}*/
  // {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    RestaurantsListComponent,
    MealsListComponent,
    SearchComponent,
    RestaurantCuisinesComponent,
    RestaurantMealsComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    NgbModule,
    MatCardModule,

    MatCheckboxModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSlideToggleModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatDatepickerModule,
    BrowserAnimationsModule,

    MatNativeDateModule,
    FormlyMatDatepickerModule,
    FormlyMatToggleModule,

    ReactiveFormsModule,
    FormlyModule.forRoot({ extras: { lazyRender: true } }),
    FormlyBootstrapModule,
    FormlyMaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
