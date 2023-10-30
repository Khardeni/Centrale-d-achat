import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { now } from 'mongoose';
import { PaiementService } from 'src/app/shared/services/paiement.service';
import { PanierService } from 'src/app/shared/services/panier.service';
import { SharedService } from 'src/app/shared/services/shared-service.service';


export interface selectClause {
  value: number;
  viewValue: string;
}

export interface livraison {
  typeLivraison: number;
  adresseLivraison: string;
  numeroDestinataire : string
}

export interface paiement {
  methodePaiement: number;
  cardNumber: string;
  expirationDate: string;
  cvv: string;
}

export interface checkoutDTO{
  paiement : paiement,
  livraison : livraison
}

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  tp : string;
  isLinear = true;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  livraison : livraison={
    adresseLivraison:"",
    numeroDestinataire:"",
    typeLivraison:1
  };
  paiement:paiement={
    cardNumber:"",
    cvv:"",
    expirationDate:null,
    methodePaiement:1
  };
  checkoutDto:checkoutDTO={
    livraison:this.livraison,
    paiement:this.paiement
  };
  typeLivraison: selectClause[] = [
    {value: 1, viewValue: 'Basic'},
    {value: 2, viewValue: 'Envelement Inclus'},
    {value: 3, viewValue: 'Urgente'}
  ];
  methodePaiement: selectClause[] = [
    {value: 1, viewValue: 'Cash'},
    {value: 2, viewValue: 'Visa ou MasterCard'},
    {value: 3, viewValue: 'Paypal'},
    {value: 4, viewValue: 'D-17'}
  ];
  lplist: any=[];
  total=0;

  constructor(private _formBuilder: FormBuilder, private paiementService: PaiementService,
    private sharedService:SharedService, private panierService:PanierService, private route:Router,
    private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      typeLivraison: ['', Validators.required],
      adresseLivraison: ['', Validators.required],
      numeroDestinataire: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      methodePaiement: ['', Validators.required]
    });

    this.panierService.getCheckoutDetailsForUser(this.sharedService.getUsername()).subscribe(data =>{
      this.lplist= data;
      console.log(this.lplist);
      this.lplist.forEach(ar => {
          this.total = this.total + (ar.prixArticle * ar.quantite);
      });

    });

  }


  checkSelect(){
    this.livraison.adresseLivraison = this.firstFormGroup.value.adresseLivraison;
    this.livraison.numeroDestinataire = this.firstFormGroup.value.numeroDestinataire;
    this.livraison.typeLivraison = this.firstFormGroup.value.typeLivraison;
    console.log(this.livraison);
  }

  check(){
    if(this.secondFormGroup.value.methodePaiement==2){
      document.getElementById("cardn").style.visibility ="visible";
      document.getElementById("cardn").style.height ="auto";
      document.getElementById("carded").style.visibility ="visible";
      document.getElementById("carded").style.height ="auto";
      document.getElementById("cardc").style.visibility ="visible";
      document.getElementById("cardc").style.height ="auto";
    }else{
      document.getElementById("cardn").style.visibility ="hidden";
      document.getElementById("cardn").style.height ="0";
      document.getElementById("carded").style.visibility ="hidden";
      document.getElementById("carded").style.height ="0";
      document.getElementById("cardc").style.visibility ="hidden";
      document.getElementById("cardc").style.height ="0";
    }
  }

  fillPayement(){
    let month=0;
    let day=0;
    this.paiement.methodePaiement = this.secondFormGroup.value.methodePaiement;
    this.paiement.cardNumber = this.secondFormGroup.value.cardNumber;
    this.paiement.cvv = this.secondFormGroup.value.cvv;
    // console.log(this.secondFormGroup.value.expirationDate);
    let dateUnformatted = this.secondFormGroup.value.expirationDate;
    this.paiement.expirationDate = dateUnformatted.getFullYear()+"-";
    if(dateUnformatted.getMonth()<10){
      this.paiement.expirationDate += "0"+dateUnformatted.getMonth()+"-";
    }else{
      this.paiement.expirationDate += dateUnformatted.getMonth()+"-";
    }
    if(dateUnformatted.getDate()<10){
      this.paiement.expirationDate += "0"+dateUnformatted.getDate()+"-";
    }else{
      this.paiement.expirationDate += dateUnformatted.getDate();
    }
  }

  checkout(){
    console.log(this.livraison);
    console.log(this.paiement);
    this.checkoutDto.livraison=this.livraison;
    this.checkoutDto.paiement=this.paiement;
    this.paiementService.checkout(this.checkoutDto,this.sharedService.getUsername()).subscribe(data => {
        console.log(data);
        this.route.navigate(['/shop/history/'+this.sharedService.getUsername()],{relativeTo:this.activatedRoute});
      },error =>
        console.log(error));
  }
}
