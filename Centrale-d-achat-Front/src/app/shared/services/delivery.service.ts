import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse, HttpHeaders, HttpRequest} from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { response } from 'express';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  constructor(private http: HttpClient) { }

  setShipped(idLivraison) {
    console.log(idLivraison+"--service");
   (this.http.get("http://localhost:8099/delivery/set-shipped/"+idLivraison) .pipe(map((response: Response) =>response))).subscribe(d=> {console.log("d= "+d);},error => console.log(error));
  }

  saveDelivery(livraison, idCommande){
    return this.http.post("http://localhost:8099/delivery/add-delivery/"+idCommande, livraison) .pipe(map((response: Response) =>response.json()))
  }

  assignDelivery(livreur, idLivraison){
    return this.http.post("http://localhost:8099/enlevement/assign-delivery/"+idLivraison, livreur) .pipe(map((response: Response) =>response.json()))
  }

  getDeliveryDetails(idLivraison){
    return this.http.get("http://localhost:8099/delivery/details/"+idLivraison) .pipe(map((response:Response) => response))
  }

  getHistory(username){
    return this.http.get("http://localhost:8099/delivery/history/"+username) .pipe(map((response:Response) => response))
  }

  getDeliveries(){
    return this.http.get("http://localhost:8099/delivery/deliveries") .pipe(map((response: Response) => response))
  }

  getCanceledDeliveries(){
    return this.http.get("http://localhost:8099/retour/canceled-deliveries").pipe(map((response: Response) => response))
  }

  cancelDelivery(idLivraison){
    return this.http.get("http://localhost:8099/retour/cancel-delivery/"+idLivraison).pipe(map((response: Response) => response.json()))
  }


}
