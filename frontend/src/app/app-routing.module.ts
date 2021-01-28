import {LoginComponent} from './NavBar component/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './NavBar component/home/home.component';
import { BasketComponent } from './NavBar component/basket/basket.component';
import { MyplantsComponent } from './My account/myplants/myplants.component';
import { StreamingComponent } from './My account/streaming/streaming.component';
import { MypurchasesComponent } from './My account/mypurchases/mypurchases.component';
import { EquipAcesComponent } from './products/equip-aces/equip-aces.component';
import { MaintRepComponent } from './products/maint-rep/maint-rep.component';
import { SeedsComponent } from './products/seeds/seeds.component';
import { ConnHydrComponent } from './products/conn-hydr/conn-hydr.component';
import { GetUploadImagesComponent } from './GetUploadimages/get-upload-images/get-upload-images.component';
import { IOTComponent } from './iot/iot.component';
import { WeatherWidgetMainComponent } from './weather-widget-main/weather-widget-main.component';

const routes: Routes =
[
  {path: "login" , component: LoginComponent},
  {path: "home",component: HomeComponent},
  {path: "basket",component: BasketComponent},
  {path: "EquipAces",component: EquipAcesComponent},
  {path: "MaintRep",component: MaintRepComponent},
  {path: "Seeds",component: SeedsComponent},
  {path: "ConnHydr",component: ConnHydrComponent},
  {path: "Myplants",component:MyplantsComponent},
  {path: "Streaming",component:StreamingComponent},
  {path: "Mypurchases",component:MypurchasesComponent},
  {path: "images",component:GetUploadImagesComponent},
  {path: "iot",component:IOTComponent},
  {path: "weather",component:WeatherWidgetMainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
