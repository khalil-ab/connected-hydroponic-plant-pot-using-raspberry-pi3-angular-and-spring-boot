import {JwtHelperService} from '@auth0/angular-jwt';
import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

type seed_info = { id: number, img : any, name: string, details: string,category: string, price:number };

@Injectable({
  providedIn: 'root'
})

export class CommunService {

  constructor(private http:HttpClient) { }

  jwt
  roles
  basket=0
  seeds=undefined
  myarray: seed_info[]=[]
  iterator: seed_info[]=[]
  SaveJwt(jwt)
  {
    this.jwt=jwt
    let paramJWT=new JwtHelperService().decodeToken(jwt);
    this.roles=paramJWT.roles
    localStorage.setItem("token",jwt)
  }

  LoadParamJwt()
  {
    if(localStorage.getItem("token"))
    {
      this.jwt=localStorage.getItem("token")
      let paramJWT=new JwtHelperService().decodeToken(this.jwt);
      this.roles=paramJWT.roles
    }
  }

  IsAuthenticated()
  {
    return this.roles
  }

  Logout()
  {
    localStorage.removeItem("token")
    this.jwt=undefined
    this.roles=undefined
  }

  isAdmin()
  {
    return this.roles.indexOf('ADMIN')>=0;
  }

  //permet de nous dire s il s agit d un user ou non
  isUser()
  {
    return this.roles.indexOf('USER')>=0;
  }

  add(id,img,name,details,category,price)
  {
    this.myarray.push
    ({
      id: id,
      img : img,
      name: name,
      details: details,
      category:category,
      price:price
    })


    /*for(var i = 0, len = this.myarray.length; i < len; i++)
    {
        console.log(this.myarray[i]); //Would give you the id of each client
    }*/
  }
}
