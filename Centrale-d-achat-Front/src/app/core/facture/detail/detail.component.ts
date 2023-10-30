import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FactureServiceService } from 'src/app/shared/services/facture-service.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})

export class DetailComponent {
  first='bonjour';
  idFact: any;
  listCommandes:any=[];
  detailGeneral:any;
  constructor(private activatedRoute: ActivatedRoute, private factureService : FactureServiceService){}

  ngOnInit(){
    this.activatedRoute.params.subscribe(s => { this.idFact=s['factid'] });
    this.factureService.getDetailCommande(this.idFact).subscribe(df=> {this.detailGeneral = df; console.log(this.detailGeneral)});
    this.factureService.getDetailFacture(this.idFact).subscribe(d=> {this.listCommandes = d;});
  }

  changeFirst(){
    this.first=(<HTMLInputElement>document.getElementById("first")).value;
    console.log(this.listCommandes);
  }
}
