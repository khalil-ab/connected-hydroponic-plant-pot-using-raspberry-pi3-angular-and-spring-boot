import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { MatDialog } from "@angular/material/dialog";
import { DialogContentExampleDialogComponent } from './../../dialog-content-example-dialog/dialog-content-example-dialog.component';
import { CommunService } from "src/app/services/commun.service";
 import { AppComponent } from "src/app/first page/app.component";

type seed_info = { id: number, img : any, name: string, details: string,category: string, price:number };

@Component({
  selector: "app-seeds",
  templateUrl: "./seeds.component.html",
  styleUrls: ["./seeds.component.css"],
})

export class SeedsComponent implements OnInit {
  constructor(private httpClient: HttpClient,private dialog: MatDialog,private com:CommunService,private ap:AppComponent) {}

  retrievedImage: any;
  cmpt = 0;
  base64Data: any;
  retrieveResonse: any;
  img = [];
  j: any;
  name: string;
  basket
  seeds
  myarray: seed_info[]=[]


  ngOnInit() {

    this.httpClient.get("http://localhost:8084/products").subscribe(
      (data) => {
        this.seeds = data;

        for (let s of this.seeds._embedded.products) {
          this.httpClient
            .get("http://localhost:8084/products/get/" + s.name)
            .subscribe((res) => {
              this.retrieveResonse = res;
              this.base64Data = this.retrieveResonse.picByte;
             // this.img[this.cmpt] = "data:image/jpeg;base64," + this.base64Data;

              this.myarray.push
              ({
                id: s.id,
                img : "data:image/jpeg;base64," + this.base64Data,
                name: s.name,
                details: s.details,
                category: s.category,
                price: s.price
              })

              //this.cmpt = this.cmpt + 1;
            });
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  getImage(imageName) {
    this.httpClient
      .get("http://localhost:8084/products/get/" + imageName)
      .subscribe((res) => {
        this.retrieveResonse = res;
        this.base64Data = this.retrieveResonse.picByte;
        this.retrievedImage = "data:image/jpeg;base64," + this.base64Data;
      });
  }

  buy(p)
  {
      const dialogRef = this.dialog.open(DialogContentExampleDialogComponent,{
        data: {name: p.name}

      });

    this.com.basket= this.com.basket+1

    this.com.add
    (
       p.id,
       p.img,
       p.name,
       p.details,
       p.category,
       p.price
    )

      /*dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });*/

      this.ap.nbr=this.ap.nbr+1
   }


}
