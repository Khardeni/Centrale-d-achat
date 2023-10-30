import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as $ from 'jquery';
import { AuthService } from 'src/app/shared/services/AuthService';
import { CurrencyService } from 'src/app/shared/services/currency.service';
import { PanierService } from 'src/app/shared/services/panier.service';
declare const jQuery: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['../../../assets/css/style.css','../../../assets/css/bootstrap.css',
              '../../../assets/css/font-awesome.min.css','../../../assets/css/responsive.css']
})
export class HeaderComponent {
  username ="";
  itemsincart=0;
  panier: any=[];
  clist :any=[];
  currency: any;
  exchangeRate: any;


  constructor(private panierService:PanierService,private currencyService:CurrencyService, 
    private auth:AuthService, private router:Router, private ar:ActivatedRoute){}
  ngOnInit(){
    this.currencyService.getCurrencyList().subscribe(d=> this.clist=d)
    this.username = localStorage.getItem("username");
    this.panierService.getPanierByUser(this.username).subscribe(data=> {this.panier=data;this.itemsincart=this.panier.nbrArticle;});
    this.username=localStorage.getItem("username");

    jQuery.noConflict();
    jQuery(document).ready(function(){
      jQuery('#customCarousel1').carousel();
    });
    this.executeMethods();
  }

  carousel1(){
    document.getElementById("carousel_indicator1").classList.add("active");
    document.getElementById("carousel1").classList.add("active");

    document.getElementById("carousel_indicator2").classList.remove("active");
    document.getElementById("carousel2").classList.remove("active");
    document.getElementById("carousel_indicator3").classList.remove("active");
    document.getElementById("carousel3").classList.remove("active");
    document.getElementById("carousel_indicator4").classList.remove("active");
    document.getElementById("carousel4").classList.remove("active");
    document.getElementById("carousel_indicator5").classList.remove("active");
    document.getElementById("carousel5").classList.remove("active");
  }
  carousel2(){
    document.getElementById("carousel_indicator2").classList.add("active");
    document.getElementById("carousel2").classList.add("active");

    document.getElementById("carousel_indicator1").classList.remove("active");
    document.getElementById("carousel1").classList.remove("active");
    document.getElementById("carousel_indicator3").classList.remove("active");
    document.getElementById("carousel3").classList.remove("active");
    document.getElementById("carousel_indicator4").classList.remove("active");
    document.getElementById("carousel4").classList.remove("active");
    document.getElementById("carousel_indicator5").classList.remove("active");
    document.getElementById("carousel5").classList.remove("active");
  }
  carousel3(){
    document.getElementById("carousel_indicator3").classList.add("active");
    document.getElementById("carousel3").classList.add("active");


    document.getElementById("carousel_indicator1").classList.remove("active");
    document.getElementById("carousel1").classList.remove("active");
    document.getElementById("carousel_indicator2").classList.remove("active");
    document.getElementById("carousel2").classList.remove("active");
    document.getElementById("carousel_indicator4").classList.remove("active");
    document.getElementById("carousel4").classList.remove("active");
    document.getElementById("carousel_indicator5").classList.remove("active");
    document.getElementById("carousel5").classList.remove("active");
  }
  carousel4(){
    document.getElementById("carousel_indicator4").classList.add("active");
    document.getElementById("carousel4").classList.add("active");

    document.getElementById("carousel_indicator1").classList.remove("active");
    document.getElementById("carousel1").classList.remove("active");
    document.getElementById("carousel_indicator2").classList.remove("active");
    document.getElementById("carousel2").classList.remove("active");
    document.getElementById("carousel_indicator3").classList.remove("active");
    document.getElementById("carousel3").classList.remove("active");
    document.getElementById("carousel_indicator5").classList.remove("active");
    document.getElementById("carousel5").classList.remove("active");
  }
  carousel5(){
    document.getElementById("carousel_indicator5").classList.add("active");
    document.getElementById("carousel5").classList.add("active");

    document.getElementById("carousel_indicator1").classList.remove("active");
    document.getElementById("carousel1").classList.remove("active");
    document.getElementById("carousel_indicator2").classList.remove("active");
    document.getElementById("carousel2").classList.remove("active");
    document.getElementById("carousel_indicator3").classList.remove("active");
    document.getElementById("carousel3").classList.remove("active");
    document.getElementById("carousel_indicator4").classList.remove("active");
    document.getElementById("carousel4").classList.remove("active");
  }

  executeMethods() {
    let count = 0;
    const interval = setInterval(() => {
      switch (count) {
        case 0:
          this.carousel1();
          break;
        case 1:
          this.carousel2();
          break;
        case 2:
          this.carousel3();
          break;
        case 3:
          this.carousel4();
          break;
        case 4:
          this.carousel5();
          break;
      }
      count = (count + 1) % 5;
    }, 5000);
  }


  changeCurrency(){
    this.currency=(<HTMLSelectElement>document.getElementById('curch')).selectedIndex;
    this.clist.forEach(_curr => {
      if(this.currency==_curr.currencyId){
        this.currencyService.getExchangeRate('TND',_curr.symbol).subscribe(d=>{this.exchangeRate=d;

          localStorage.setItem("exr",this.exchangeRate);
          localStorage.setItem("exs",_curr.symbol);
          console.log(this.exchangeRate);
        });
      }
    });
  }

  logout(){
    this.auth.clear();
    this.router.navigate(['/login'],{relativeTo:this.ar})
  }

}
