import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DeliveryService } from 'src/app/shared/services/delivery.service';
import { FactureServiceService } from 'src/app/shared/services/facture-service.service';

@Component({
  selector: 'app-history-detail',
  templateUrl: './history-detail.component.html',
  styleUrls: ['./history-detail.component.css']
})
export class HistoryDetailComponent {
  idCommande: any;
  idFact: any;
  listCommandes: any=[];
  total=0;
  detailGeneral: any;
  annule:0;
  all: any;
  listCommandesN: any;
  rate: any;
  symbol: any;
  constructor(private ar:ActivatedRoute, private factureService: FactureServiceService, private deliveryService:DeliveryService){

  }

  ngOnInit(){
    let i=0;
    this.symbol=localStorage.getItem("exs");
    this.rate=localStorage.getItem("exr");
    this.ar.params.subscribe(s=> this.idCommande = s["idcommande"])
    this.factureService.getFactureByCommande(this.idCommande).subscribe(data =>{
      this.all= data;
      this.idFact = this.all.factureId;
      this.factureService.getDetailCommande(this.idFact).subscribe(df=> {
        this.detailGeneral = df;
        this.annule = this.detailGeneral.etatFacture;
      });
      this.factureService.getDetailFacture(this.idFact).subscribe(d=> {
        this.listCommandesN = d;
        this.listCommandesN.forEach(ar => {

        ar.prixArticle = ar.prixArticle * this.rate;
        this.listCommandes[i]=ar;
        i=i+1;
          this.total = this.total + (ar.prixArticle * ar.quantite);
        });
      });
    });
  }

  cancelDelivery(){
    this.deliveryService.cancelDelivery(this.idCommande).subscribe(d=> console.log(d));
  }
}
