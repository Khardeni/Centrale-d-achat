import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PanierService } from 'src/app/shared/services/panier.service';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent {
  username: any;
  lplist: any=[];
  total=0;
  lplistN: any=[];
  rate: any;
  symbol: any;
  constructor(private ar:ActivatedRoute, private panierService: PanierService){}

  ngOnInit(){
    this.symbol=localStorage.getItem("exs");
    this.rate=localStorage.getItem("exr");
    let i=0;
    this.total=0;
    this.username=localStorage.getItem('username');
    this.panierService.getLPbyUser(this.username).subscribe(data =>{
      // this.listProduitsN = data;
      // this.listProduitsN.forEach(element => {
      //   element.prixHT=element.prixHT*this.rate;
      //   this.listProduits[i]=element;
      //   i=i+1;
      // });
      this.lplistN= data;
      console.log(this.lplistN);
      this.lplistN.forEach(ar => {
        ar.prixArticle = ar.prixArticle * this.rate;
        this.lplist[i]=ar;
        i=i+1;
        if(ar.status==-1){
          this.total = this.total + (ar.prixArticle * ar.quantite);
        }
      });

    });
  }

  changeStatus(idc:any,pr:any){
    console.log(idc);
    this.panierService.updateStatus(idc).subscribe(d=> {
      //console.log(d);
      this.ngOnInit();});
  }


}


