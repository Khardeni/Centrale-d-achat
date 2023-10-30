import { Component } from '@angular/core';
import { PaiementService } from 'src/app/shared/services/paiement.service';


@Component({
  selector: 'app-paiement',
  templateUrl: './paiement.component.html',
  styleUrls: ['./paiement.component.css']
})
export class PaiementComponent {
  listPaiements:any=[];
  constructor(private paiementService : PaiementService){}

  ngOnInit(){
    document.getElementById("paiement_element").classList.add('active');
    this.paiementService.getAllPaiements().subscribe(data => this.listPaiements = data);
  }

  ngOnDestroy(){
    document.getElementById("paiement_element").classList.remove('active');
  }
}
