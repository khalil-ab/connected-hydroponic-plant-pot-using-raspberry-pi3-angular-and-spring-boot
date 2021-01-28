import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent     {

  constructor(private router:Router) {  }

  GotoEquipAces()
  {
    this.router.navigateByUrl("/EquipAces");
  }

  GotoSeeds()
  {
    this.router.navigateByUrl("/Seeds");
  }

  GotoMainRep()
  {
    this.router.navigateByUrl("/MaintRep");
  }

  GoToConnHydr()
  {
    this.router.navigateByUrl("/ConnHydr");
  }

}
