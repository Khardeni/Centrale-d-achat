import { Component, ViewChild } from '@angular/core';
import { HealthService } from 'src/app/shared/services/health.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Route, Router } from '@angular/router';
import * as $ from 'jquery';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexStroke,
  ApexTitleSubtitle,
  ApexDataLabels,
  ApexMarkers,
  ApexFill
} from "ng-apexcharts";
import { LivreurService } from 'src/app/shared/services/livreur.service';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  markers: ApexMarkers;
  stroke: ApexStroke;
  dataLabels: ApexDataLabels;
  title: ApexTitleSubtitle;
};

var authErrors:number[]=[];
var pathErrors:number[]=[];
var BackErrors:number[]=[];
var UErrors:number[]=[];
@Component({
  selector: 'app-health',
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.css']
})

export class HealthComponent {

  @ViewChild("chart") chart: ChartComponent;
  public authChartOptions: Partial<ChartOptions>;

  @ViewChild("pathchart") pathchart: ChartComponent;
  public pathChartOptions: Partial<ChartOptions>;

  @ViewChild("backchart") backchart: ChartComponent;
  public backChartOptions: Partial<ChartOptions>;

  @ViewChild("uchart") uchart: ChartComponent;
  public uChartOptions: Partial<ChartOptions>;

  progress ="";
  number=0;

  listBackups: any;
  listFact: any=[];
  listReplication: any=[];

  listFactsize: any;
  listReplicationSize: any;
  listLogs: any=[];

  constructor(private healthService:HealthService,private spinner: NgxSpinnerService,
        private route:Router, private livreurService:LivreurService){  }

  ngOnInit(){
    localStorage.setItem("monitoringMode","1");
    this.healthService.getErrors(1).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      authErrors.push(this.number)
    });

    this.healthService.getErrors(3).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      pathErrors.push(this.number)
    });

    this.healthService.getErrors(2).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      BackErrors.push(this.number)
    });

    this.healthService.getErrors(4).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      UErrors.push(this.number)
    });

    this.healthService.getFactureHealth().subscribe(d=> {
      this.listFact = d;
      this.listFactsize=this.listFact.length;
      },error=> console.log(error));

      this.healthService.getReplication().subscribe(d=> {
        this.listReplication = d;
        this.listReplicationSize=this.listReplication.length;
        },error=> console.log(error));

    this.initAuthChart();
    this.initPathChart();
    this.initBackChart();
    this.initUChart();
    this.refreshChart();

    // window.location.reload();
  }

  ngOnDestroy(){
    localStorage.setItem("monitoringMode",'0');
  }

  deleteLivreur(id:any){
    let indexofid = id.indexOf("Id=");
    let idLivreur = id.slice(indexofid+3,id.indexOf(",",indexofid+3));
    this.livreurService.deleteLivreur(idLivreur).subscribe(d=>console.log(d));
  }

  initAuthChart(){
    this.authChartOptions = {
      series: [
        {
          name: "Error Count",
          data: authErrors,
          color:'#65FE08'
        }
      ],
      chart: {
        animations:{
          enabled:false,
        },
        type: "line",
        height: 300,
        width:500,
        background: '#000',
        foreColor:'#65FE08'
      },
      stroke: {
        curve: "stepline"
      },
      dataLabels: {
        style: {
          colors: ['#F44336', '#E91E63', '#9C27B0']
        },
        enabled: false
      },
      title: {
        text: "Authorization and Authentication Errors",
        align: "left"
      },
      markers: {
        colors: ['#F44336', '#E91E63', '#9C27B0'],
        hover: {
          sizeOffset: 4
        }
      }
    };
  }

  initPathChart(){
    this.pathChartOptions = {
      series: [
        {
          name: "Error Count",
          data: pathErrors,
          color:'#65FE08'
        }
      ],
      chart: {
        animations:{
          enabled:false,
        },
        type: "line",
        height: 300,
        width:500,
        background: '#000',
        foreColor:'#65FE08'
      },
      stroke: {
        curve: "stepline"
      },
      dataLabels: {
        style: {
          colors: ['#F44336', '#E91E63', '#9C27B0']
        },
        enabled: false
      },
      title: {
        text: "Path Errors",
        align: "left"
      },
      markers: {
        colors: ['#F44336', '#E91E63', '#9C27B0'],
        hover: {
          sizeOffset: 4
        }
      }
    };
  }

  initBackChart(){
    this.backChartOptions = {
      series: [
        {
          name: "Error Count",
          data: BackErrors,
          color:'#65FE08'
        }
      ],
      chart: {
        animations:{
          enabled:false,
        },
        type: "line",
        height: 300,
        width:500,
        background: '#000',
        foreColor:'#65FE08'
      },
      stroke: {
        curve: "stepline"
      },
      dataLabels: {
        style: {
          colors: ['#F44336', '#E91E63', '#9C27B0']
        },
        enabled: false
      },
      title: {
        text: "Server-Side Errors",
        align: "left"
      },
      markers: {
        colors: ['#F44336', '#E91E63', '#9C27B0'],
        hover: {
          sizeOffset: 4
        }
      }
    };
  }

  initUChart(){
    this.uChartOptions = {
      series: [
        {
          name: "Error Count",
          data: UErrors,
          color:'#65FE08'
        }
      ],
      chart: {
        animations:{
          enabled:false,
        },
        type: "line",
        height: 300,
        width:500,
        background: '#000',
        foreColor:'#65FE08'
      },
      stroke: {
        curve: "stepline"
      },
      dataLabels: {
        style: {
          colors: ['#F44336', '#E91E63', '#9C27B0']
        },
        enabled: false
      },
      title: {
        text: "Unknown Errors",
        align: "left"
      },
      markers: {
        colors: ['#F44336', '#E91E63', '#9C27B0'],
        hover: {
          sizeOffset: 4
        }
      }
    };
  }

  onTheMinFunc(){
    //console.log(this.healthService.getAuthErrors());
    this.healthService.getErrors(1).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      authErrors.push(this.number)
    });
    this.healthService.getErrors(3).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      pathErrors.push(this.number)
    });
    this.healthService.getErrors(2).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      BackErrors.push(this.number)
    });
    this.healthService.getErrors(4).subscribe(d =>{
      this.number = JSON.parse(d.toString());
      UErrors.push(this.number)
    });

    this.initAuthChart();
    this.initPathChart();
    this.initBackChart();
    this.initUChart();
  }

  refreshChart() {
    setInterval(this.onTheMinFunc.bind(this), 2000);
  }

  simulate404(){
    this.healthService.generate404().subscribe(d=>console.log(d));
  }

  simulate500(){
    this.healthService.generate500().subscribe(d=>console.log(d));
  }

  simulate401(){
    this.healthService.generate401().subscribe(d=>console.log(d));
  }

  simulatex(){
    this.healthService.generatex().subscribe(d=>console.log(d));
  }

  loadLogs(id:any){
    this.healthService.getLogs(id).subscribe(d=> {this.listLogs=d;
    console.log(this.listLogs)},error => console.log(error))
  }
}
