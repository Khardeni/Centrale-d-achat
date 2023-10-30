import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeliveryService } from 'src/app/shared/services/delivery.service';
import { FactureServiceService } from 'src/app/shared/services/facture-service.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent {
  username="";
  ordersIds:any;
  constructor(private activatedRoute:ActivatedRoute, private factureService:FactureServiceService){}

  ngOnInit(){
    this.activatedRoute.params.subscribe(s => { this.username=s['username'] });
    this.factureService.getOrderIdsList(this.username).subscribe(data => {
      this.ordersIds=data;
    })
  }
}
