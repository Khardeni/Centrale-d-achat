import { Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { NgxSpinnerService } from "ngx-spinner";

//let template = './app.component.html';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css',"./assets/css/app.css"]
})

export class AppComponent {

  interval: NodeJS.Timer;
  domLoaded: boolean;

  constructor(private router:Router) { }
  userType="";
  monitoringMode=0;
  ngOnInit() {
    if(!(localStorage.getItem("monitoringMode"))){
      localStorage.setItem("monitoringMode","0");
    }
    this.monitoringMode= parseInt(localStorage.getItem("monitoringMode"));
    this.userType= localStorage.getItem("user");
    if(this.userType=='admin'){
      // this.router.navigate(['/dashboard'])
    }else if(this.userType=='user'){
     //this.router.navigate(['/shop/home'])
    }
  }

   ngAfterViewInit() {

  if (!sessionStorage.getItem('siteInit')) {
    this.interval = setInterval(() => {
    this.domLoaded = true;
    if(!localStorage.getItem("exr")){
      localStorage.setItem("exr","1");
    }
    if(!localStorage.getItem("exs")){
      localStorage.setItem("exs","TND");
    }
    if(!localStorage.getItem("monitoringMode")){
      localStorage.setItem("monitoringMode","0");
    }
    if(!localStorage.getItem("user")){
      localStorage.setItem("user","user");
    }
    if(!localStorage.getItem("username")){
      localStorage.setItem("username","");
    }
    if(!localStorage.getItem("token")){
      localStorage.setItem("token","");
    }

     console.log("yes");
     sessionStorage.setItem('siteInit', 'true');
    }, 5000);
   } else {
    console.log("no");


    this.domLoaded = true;
   }



   }


}
