import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { HealthService } from 'src/app/shared/services/health.service';

@Component({
  selector: 'app-backups',
  templateUrl: './backups.component.html',
  styleUrls: ['./backups.component.css']
})
export class BackupsComponent {
  listBackups: any;
  progress ="";

  constructor(private healthService:HealthService,private spinner: NgxSpinnerService, private route:Router){  }

  ngOnInit(){
    this.healthService.getAvailableBackups().subscribe(d=>{
      this.listBackups = d;
      console.log(this.listBackups);
    })
  }

  wait(ms){
    var start = new Date().getTime();
    var end = start;
    while(end < start + ms) {
      end = new Date().getTime();
    }
  }

  recoverDatabase(filename:any){
    this.progress="Initiating Database Recovery..";
    this.spinner.show();
    this.healthService.updateDatabase(filename).subscribe(d=>{
      this.wait(5000);
      this.spinner.hide();
      this.route.navigate['/dashboard'];
    },error => console.log(error));
  }
}
