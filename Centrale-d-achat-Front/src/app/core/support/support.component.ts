import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

type Help = {
    compName:String,
    routeToComponent:String,
    description:String
  };

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent {
    search="";

    help:Help={
      compName:"",
      routeToComponent:"",
      description:""
    };
    listHelp:Help[]=[];
    constructor(private activatedRoute: ActivatedRoute){}
    ngOnInit(){
      this.activatedRoute.params.subscribe(s => { this.search=s['text'] });
      console.log(this.search);
      if(this.search.includes("facture")){
        this.help.compName = 'Liste Factures';
        this.help.description = 'Consulter tous les factures, chercher une facture spécifique, chercher par date ou client etc..';
        this.help.routeToComponent='/facture';
        this.listHelp.push(this.help);
        console.log("supposedly cards have been displayed");
      }else if(this.search.includes("paiement")){
        this.help.compName = 'Liste Paiement';
        this.help.description = 'Consulter tous les paiements, chercher une paiement spécifique, chercher par date ou client etc..';
        this.help.routeToComponent='/paiement';
        this.listHelp.push(this.help);
        console.log("supposedly cards have been displayed");
      }else{
        document.getElementById("not-found").style.visibility = 'visible';
        document.getElementById("not-found").style.height = 'auto';
      }
    }
}
