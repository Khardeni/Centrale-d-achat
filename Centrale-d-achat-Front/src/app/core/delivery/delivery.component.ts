import { Component } from '@angular/core';
import { DeliveryService } from 'src/app/shared/services/delivery.service';


import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {startWith} from 'rxjs/operators';
import {map} from 'rxjs/operators';
import { LivreurService } from 'src/app/shared/services/livreur.service';

export interface Livreur {
  nomLivreur: string,
  societeLivraison: string,
  secteur: string,
  numLivreur: string
}

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.css']
})


export class DeliveryComponent {

  listDeliveries:any=[];
  detailedList: any=[];
  options=[];
  optionss:any=[];
  livreur:Livreur={
    nomLivreur: "",
    societeLivraison: "",
    secteur: "",
    numLivreur: ""
  };
  value="";
  Form: FormGroup;
  myControl: FormControl = new FormControl();
  illControl: FormControl = new FormControl();
  numControl: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;
  dateAjout: any;
  livraisonId: any;

  constructor(private deliveryService : DeliveryService, private livreurService:LivreurService, private _formBuilder: FormBuilder){}

  ngOnInit(){



    document.getElementById("delivery_element").classList.add('active');
    this.Form = this._formBuilder.group({
      nomLivreur: ['', this.myControl],
      societeLivraison: ['', Validators.required],
      secteur: [''],
      numLivreur: ['', this.numControl]
    });

    this.livreurService.FillSuggestionsListWithAll().subscribe(data => {
      this.optionss = data;
      console.log(data);
      this.optionss.forEach(l =>  this.options.push(l.nomLivreur));
      // console.log(this.optionss);
      this.filteredOptions = this.myControl.valueChanges.pipe(
        map(val => this.filter(val))
      );
      this.filteredOptions.subscribe((e) => {
        // console.log(e);
      });
    });
    this.deliveryService.getDeliveries().subscribe(data => {
      this.listDeliveries = data;
      this.listDeliveries.forEach(delivery => {
        this.deliveryService.getDeliveryDetails(delivery.livraisonId).subscribe(details => {this.detailedList.push(details);})
      });
    });
  }



  ngOnDestroy(){
    document.getElementById("delivery_element").classList.remove('active');
  }

  getSuggestions(){
    this.options=[];
    this.value = (<HTMLInputElement>document.getElementById("nomLivreur")).value;
    // console.log(this.value);
    if(this.value!=''){
      this.livreurService.FillSuggestionsList(this.value).subscribe(data2 => {
        this.optionss = data2;
        this.optionss.forEach(l =>  {
          this.options.push(l.nomLivreur);
            if(this.value==l.nomLivreur){
              this.Form.get('numLivreur').setValue(l.numLivreur);
              this.Form.get('societeLivraison').setValue(l.societeLivraison);
              this.Form.get('secteur').setValue(l.secteur);
              this.dateAjout=l.dateAjout;
            }else{
              this.Form.get('numLivreur').setValue("");
              this.Form.get('societeLivraison').setValue("");
              this.Form.get('secteur').setValue("");
              this.dateAjout=null;
            }
        })
      });
    }else{
      this.livreurService.FillSuggestionsListWithAll().subscribe(data2 => {
        this.optionss = data2;
        // console.log(data2 +"  //// "+this.optionss);
        this.optionss.forEach(l =>  this.options.push(l.nomLivreur))
      });
    }
  }


  filter(val: string): string[] {
    return this.options.filter(option => typeof option === 'string' && option.toLowerCase().indexOf(val.toLowerCase()) === 0);
  }
  displayModal(livraisonId:any){
    console.log("displayed");
    this.livraisonId = livraisonId;
    document.getElementById("myModal11").style.visibility =" visible";
  }
  closeModal(){
    document.getElementById("myModal11").style.visibility =" hidden";
  }
  setShipped(idLivraison:any){
    console.log(idLivraison);
    this.deliveryService.setShipped(idLivraison);
  }
  sendData(){
    console.log(this.livreur);
    console.log(this.livraisonId);
    this.deliveryService.assignDelivery(this.livreur,this.livraisonId).subscribe(d=> {console.log(d)},error => console.log(error));
    document.getElementById("myModal11").style.visibility =" hidden";
  }
  cancelDelivery(id:any){
    this.deliveryService.cancelDelivery(id).subscribe(d=> console.log(d));
  }


}
