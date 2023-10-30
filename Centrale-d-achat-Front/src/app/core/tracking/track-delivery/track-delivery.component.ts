import { Component } from '@angular/core';
import { LivreurService } from 'src/app/shared/services/livreur.service';

@Component({
  selector: 'app-track-delivery',
  templateUrl: './track-delivery.component.html',
  styleUrls: ['./track-delivery.component.css']
})
export class TrackDeliveryComponent {
  trackValue: string = '';
  dto:any;
  constructor(private livreurService : LivreurService){}
  trackDelivery(){
    this.livreurService.RequestTrackDelivery(this.trackValue).subscribe(data => this.dto = data);
    console.log(this.dto);

    document.getElementById("track_container").style.paddingTop = "3%";
    document.getElementById("result_table").style.visibility = "visible";
    // document.getElementById("track_container").style.backgroundColor = "#f0f0f0";
  }
}
