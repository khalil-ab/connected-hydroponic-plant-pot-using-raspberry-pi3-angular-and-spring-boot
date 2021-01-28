import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-iot',
  templateUrl: './iot.component.html',
  styleUrls: ['./iot.component.css']
})
export class IOTComponent implements OnInit {

  url = 'http://localhost:8080/websocket'
  client: any;
  greeting: string;

  ngOnInit() {
	this.title.setTitle('Angular Spring Websocket');
  }

  constructor(private title: Title){
    this.connection();
  }

  connection(){
    let ws = new SockJS(this.url);
    this.client = Stomp.over(ws);
    let that = this;

    this.client.connect({}, function(frame) {
      that.client.subscribe("/topic/greeting", (message) => {
        if(message.body) {
          this.greeting = message.body;
		  //$(".msg").append(this.greeting)
		  $(".msg").html(this.greeting)
		  //alert(this.greeting);
		  //console.log(this.greeting);
        }
      });
    });
  }

}
