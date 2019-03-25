import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardContainerComponent } from 'src/app/modules/muzix/components/card-container/card-container.component';
import { WishListComponent } from 'src/app/modules/muzix/components/wish-list/wish-list.component';
import { LogoComponent } from 'src/app/modules/muzix/components/logo/logo.component';

const routes: Routes = [

  {
    path:"",
    component: CardContainerComponent,
    data: {country: "india"}
  },
  {
    path:"Australia",
    component: CardContainerComponent,
    data: {country: "australia"}
  },
  {
    path:"Canada",
    component: CardContainerComponent,
    data: {country: "canada"}
  },
  {
    path:"China",
    component: CardContainerComponent,
    data: {country: "china"}
  },
  {
    path:"Germany",
    component: CardContainerComponent,
    data: {country: "germany"}
  },
  {
    path:"India",
    component: CardContainerComponent,
    data: {country: "india"}
  },
  {
    path:"Ireland",
    component: CardContainerComponent,
    data: {country: "ireland"}
  },
  {
    path:"Italy",
    component: CardContainerComponent,
    data: {country: "italy"}
  },
  {
    path:"Japan",
    component: CardContainerComponent,
    data: {country: "japan"}
  },
  {
    path:"Spain",
    component: CardContainerComponent,
    data: {country: "spain"}
  },
  {
    path:"Sweden",
    component: CardContainerComponent,
    data: {country: "sweden"}
  },
  {
    path:"Ukraine",
    component: CardContainerComponent,
    data: {country: "ukraine"}
  },  
  {
    path:"UnitedStates",
    component: CardContainerComponent,
    data: {country: "united%20states"}
  },
  
  {
    path:"WishList",
    component: WishListComponent
  },
  {
    path:"Logo",
    component: LogoComponent
  }  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
