import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { CommunService } from "./../services/commun.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  title = "CatalogueWebApp";
  loginpg = 0;
  homepg = 1;
  nbr

  constructor(private router: Router, private com: CommunService) {}

  ngOnInit(): void {
    this.com.LoadParamJwt();
    this.nbr=this.com.myarray.length
    }

  IsAuthenticated() {
    return this.com.IsAuthenticated();
  }

  Logout() {
    this.com.Logout();
  }

  isAdmin() {
    return this.com.isAdmin();
  }
  isUser() {
    return this.com.isUser();
  }

  GotoLoginPg() {
    this.loginpg = 1;
    this.homepg = 0;
    this.router.navigateByUrl("/login");
  }

  GotoHomePg() {
    this.loginpg = 0;
    this.homepg = 1;
    this.router.navigateByUrl("/home");
  }

  GotoBasketPg() {
    this.router.navigateByUrl("/basket");
  }
}
