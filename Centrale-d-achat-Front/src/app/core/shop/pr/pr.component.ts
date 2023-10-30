import { Component } from '@angular/core';
import { PanierService } from 'src/app/shared/services/panier.service';
import { ProduitService } from 'src/app/shared/services/produit.service';

export interface Product{
  nomArticle:string;
  imagee:string;
  prixHT:number;
  articleId:number;
}

export interface LpDTO{
  idArticle:number;
  quantite:number;
  idPanier:number;
}
@Component({
  selector: 'app-pr',
  templateUrl: './pr.component.html',
  styleUrls: ['./pr.component.css']
})
export class PrComponent {
  listProduitsN:any=[];
  listProduits:any=[];
  product: Product={
    imagee:'',
    nomArticle:'',
    prixHT:0,
    articleId:0
  };

  lpDTO: LpDTO={
    idArticle:0,
    idPanier:0,
    quantite:0
  }
  quantite:any=0;
  username: string='';
  panier: any=[];
  rate: any;
  symbol: any;
  constructor(private produitService: ProduitService, private panierService : PanierService) {   }

  ngOnInit(){
    this.symbol=localStorage.getItem("exs");
    this.rate=localStorage.getItem("exr");
    console.log(this.rate);
    let i=0;
    this.produitService.getAllProducts().subscribe(data => {
      this.listProduitsN = data;
      this.listProduitsN.forEach(element => {
        element.prixHT=element.prixHT*this.rate;
        this.listProduits[i]=element;
        i=i+1;
      });
    });
 console.log(this.listProduits)  ;

}

addToCart() {
  // Check if the flag exists in the local storage
  const panierCreated = localStorage.getItem('panierCreated');
  const userId =localStorage.getItem('username');


  if (!panierCreated) {
    // Call the createPanier method to create the panier for the user
    this.panierService.createPanier(userId).subscribe(() => {
      // Set the flag in the local storage
      localStorage.setItem('panierCreated', 'true');
    });
  }
}

displayModal(produit:any){
  document.getElementById('add-lp-modal').classList.add('show');
  document.getElementById('add-lp-modal').classList.remove('fade');
  this.product.nomArticle=produit.nomArticle;
  this.product.prixHT=produit.prixHT;
  this.product.imagee=produit.imagee;
  this.product.articleId=produit.articleId;
}

closeModal(){
  document.getElementById('add-lp-modal').classList.remove('show');
  document.getElementById('add-lp-modal').classList.add('fade');
}

increase(){
  let quantitech = (<HTMLInputElement>document.getElementById("quantite")).value;
  let quantite = parseInt(quantitech);
  quantite = quantite +1;
  quantitech = quantite.toString();
  (<HTMLInputElement>document.getElementById("quantite")).value = quantitech;
}

decrease(){
  let quantitech = (<HTMLInputElement>document.getElementById("quantite")).value;
  let quantite = parseInt(quantitech);
  if(quantite>0 ){
    quantite = quantite -1;
    (<HTMLInputElement>document.getElementById("quantite")).value = quantite.toString();
  }else{
    (<HTMLInputElement>document.getElementById("quantite")).value = ('0').toString();
  }
}

saveToLP(articleId:any){
  this.quantite=(<HTMLInputElement>document.getElementById("quantite")).value;
  this.username=localStorage.getItem("username");
  this.panierService.getPanierByUser(this.username).subscribe(data => {
    this.panier = data;
    // console.log(this.panier.panierId);
    this.lpDTO.idPanier=this.panier.panierId;
    this.lpDTO.idArticle=articleId;
    this.lpDTO.quantite=this.quantite;
    this.panierService.addLP(this.lpDTO).subscribe(d=> console.log(d));

  document.getElementById('add-lp-modal').classList.remove('show');
  document.getElementById('add-lp-modal').classList.add('fade');
  });
}

}
