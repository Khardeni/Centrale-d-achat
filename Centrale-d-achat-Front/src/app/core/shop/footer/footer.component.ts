import { Component } from '@angular/core';
import * as $ from 'jquery';
declare const jQuery: any;

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['../../../assets/css/style.css','../../../assets/css/bootstrap.css',
              '../../../assets/css/font-awesome.min.css','../../../assets/css/responsive.css']
})
export class FooterComponent {

  ngOnInit(){
    //myMap();
    this.executeMethods();
  }

  carousel1(){
    document.getElementById("carousel_indicator6").classList.add("active");
    document.getElementById("carousel6").classList.add("active");

    document.getElementById("carousel_indicator7").classList.remove("active");
    document.getElementById("carousel7").classList.remove("active");
    document.getElementById("carousel_indicator8").classList.remove("active");
    document.getElementById("carousel8").classList.remove("active");
  }
  carousel2(){
    document.getElementById("carousel_indicator7").classList.add("active");
    document.getElementById("carousel7").classList.add("active");

    document.getElementById("carousel_indicator8").classList.remove("active");
    document.getElementById("carousel8").classList.remove("active");
    document.getElementById("carousel_indicator6").classList.remove("active");
    document.getElementById("carousel6").classList.remove("active");
  }
  carousel3(){
    document.getElementById("carousel_indicator8").classList.add("active");
    document.getElementById("carousel8").classList.add("active");


    document.getElementById("carousel_indicator6").classList.remove("active");
    document.getElementById("carousel6").classList.remove("active");
    document.getElementById("carousel_indicator7").classList.remove("active");
    document.getElementById("carousel7").classList.remove("active");
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
      }
      count = (count + 1) % 5;
    }, 5000);
  }

  // display: any;
  // center: google.maps.LatLngLiteral = {
  //     lat: 24,
  //     lng: 12
  // };
  // zoom = 4;
  // moveMap(event: google.maps.MapMouseEvent) {
  //   if (event.latLng != null) this.center = (event.latLng.toJSON());
  // }
  // move(event: google.maps.MapMouseEvent) {
  //   if (event.latLng != null) this.display = event.latLng.toJSON();
  // }

}


