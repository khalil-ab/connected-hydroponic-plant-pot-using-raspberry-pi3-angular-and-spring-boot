import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { LoginComponent} from './NavBar component/login/login.component';
import { FormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './first page/app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './NavBar component/home/home.component';
import { BasketComponent } from './NavBar component/basket/basket.component';
import { MyplantsComponent } from './My account/myplants/myplants.component';
import { StreamingComponent } from './My account/streaming/streaming.component';
import { MypurchasesComponent } from './My account/mypurchases/mypurchases.component';
import { MaintRepComponent } from './products/maint-rep/maint-rep.component';
import { EquipAcesComponent } from './products/equip-aces/equip-aces.component';
import { SeedsComponent } from './products/seeds/seeds.component';
import { ConnHydrComponent } from './products/conn-hydr/conn-hydr.component';
import { GetUploadImagesComponent } from './GetUploadimages/get-upload-images/get-upload-images.component';
import { IOTComponent } from './iot/iot.component';
import { WeatherWidgetMainComponent } from './weather-widget-main/weather-widget-main.component';
import { MatDialogModule } from '@angular/material/dialog';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MessageBoxComponent } from './tsc-ui/message-box.component';
import { MatSliderModule, MatBadgeModule } from '@angular/material';
import { DialogContentExampleDialogComponent } from './dialog-content-example-dialog/dialog-content-example-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BasketComponent,
    MyplantsComponent,
    StreamingComponent,
    MypurchasesComponent,
    SeedsComponent,
    MaintRepComponent,
    EquipAcesComponent,
    ConnHydrComponent,
    GetUploadImagesComponent,
    LoginComponent,
    IOTComponent,
    WeatherWidgetMainComponent,
    MessageBoxComponent,
    DialogContentExampleDialogComponent
  ],
  imports: [

  BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DragDropModule,
    MatButtonModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatBadgeModule

  ],
  exports: [
    DragDropModule,
    MatButtonModule,
    MatDialogModule,
    MessageBoxComponent,
    MatBadgeModule
  ],
  entryComponents: [ MessageBoxComponent,DialogContentExampleDialogComponent ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
