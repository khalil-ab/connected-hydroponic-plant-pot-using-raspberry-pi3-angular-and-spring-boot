import {CommunService} from '../../services/commun.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http:HttpClient,private c:CommunService,private router:Router) { }

  ngOnInit() {
  }

  onLogin(data)
  {
    this.http.post("http://localhost:8085/login",data,{observe:'response'}).subscribe(
      data=>{
        let jwt=data.headers.get('Authorization');
        this.c.SaveJwt(jwt)
        this.router.navigateByUrl("/");
      },err=>{
        console.log(err)
      }
    )
  }

}
