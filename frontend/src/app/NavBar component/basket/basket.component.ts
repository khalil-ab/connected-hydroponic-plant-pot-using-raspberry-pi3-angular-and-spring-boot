import { Component, OnInit } from '@angular/core';
import { CommunService } from 'src/app/services/commun.service';
import { AppComponent } from 'src/app/first page/app.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  basket
  myarray
  constructor(private com:CommunService,private ap:AppComponent,private router:Router) { }
  value:string="value"
  j=1
  txt:number[]=[]
  sum_price

  ngOnInit()
  {
    this.basket=this.com.basket
    this.myarray=this.com.myarray

    this.ap.nbr=0

    this.router.navigateByUrl("/basket")
    this.sum_price=0
    /*for(var i = 0, len = this.myarray.length; i < len; i++)
    {
        console.log(this.myarray[i]); //Would give you the id of each client
    }*/

  }

  onDeleteCat(id)
  {
   for(var i = 0, len = this.myarray.length; i < len; i++)
    {

      if(this.myarray[i].id==id)
      {
        this.myarray.splice(i,1);
        this.basket=this.basket-1
        this.com.basket=this.com.basket-1
        break
      }
    }
  }

  increment()
  {
    this.txt.push(1)
  }

  sum(p)
  {
    this.sum_price=this.sum_price+p
    console.log(this.sum_price)
  }

  initialize()
  {
    this.sum_price=0
  }

}
