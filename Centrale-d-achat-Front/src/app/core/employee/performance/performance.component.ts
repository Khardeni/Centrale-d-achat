import { Component,ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PerformanceService } from 'src/app/shared/services/performance.service';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { faRemove } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PerformanceComponent {
  star=faStar;
  saveIcon=faSave;
  deleteIcon=faRemove;
  rating:any=0;
  idEmployee: any;
  listPerformances: any=[];
  constructor(private activatedRoute:ActivatedRoute, private route:Router, private performanceService:PerformanceService){}

  ngOnInit(){
    this.activatedRoute.params.subscribe(s => { this.idEmployee=s['idEmployee'] });
    //console.log(this.idEmployee);
    this.performanceService.getPerformanceByIdEmployee(this.idEmployee).subscribe(d=> this.listPerformances = d);
  }

  createRange(number){
    // return new Array(number);
    return new Array(number).fill(0)
      .map((n, index) => index + 1);
  }

  hover1(){
    document.getElementById("star1").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star2").style.color = 'black';
    document.getElementById("star3").style.color = 'black';
    document.getElementById("star4").style.color = 'black';
    document.getElementById("star5").style.color = 'black';
    this.rating=1;
  }

  hover2(){
    document.getElementById("star1").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star2").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star3").style.color = 'black';
    document.getElementById("star4").style.color = 'black';
    document.getElementById("star5").style.color = 'black';
    this.rating=2;
  }

  hover3(){
    document.getElementById("star1").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star2").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star3").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star4").style.color = 'black';
    document.getElementById("star5").style.color = 'black';
    this.rating=3;
  }

  hover4(){
    document.getElementById("star1").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star2").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star3").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star4").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star5").style.color = 'black';
    this.rating=4;
  }

  hover5(){
    document.getElementById("star1").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star2").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star3").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star4").style.color = 'rgb(252, 244, 19)';
    document.getElementById("star5").style.color = 'rgb(252, 244, 19)';
    this.rating=5;
  }

  save(){
    this.performanceService.addPerformance(this.idEmployee,this.rating).subscribe(d=> console.log(d));
    this.route.navigate(['/employee/performance/'+this.idEmployee],{relativeTo:this.activatedRoute})
    //console.log(this.rating);
  }

  deletePerf(id:any){
    this.performanceService.deletePerf(id).subscribe(d=> console.log(d));
  }

}
