import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse, HttpHeaders, HttpRequest} from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class FactureServiceService {

constructor(private http: HttpClient) { }

    saveFacture(facture, idCommande){
      return this.http.post("http://localhost:8099/facture/add-facture/"+idCommande, facture) .pipe(map((response: Response) =>response.json()))
    }

    getFactures(){
      return this.http.get("http://localhost:8099/facture/factures") .pipe(map((response: Response) => response))
    }

    getDetailFacture(idFacture : any){
      return this.http.get("http://localhost:8099/lignep/get-history/"+idFacture) .pipe(map((response: Response) => response))
    }

    getOrderIdsList(username : any){
      return this.http.get("http://localhost:8099/order/get-by-user/"+username) .pipe(map((response: Response) => response))
    }

    getDetailCommande(idFact: any) {
      return this.http.get("http://localhost:8099/facture/detail/"+idFact) .pipe(map((response: Response) => response))
    }

    getFacturesByDate(dateFrom,dateTo){
      return this.http.get("http://localhost:8099/facture/get-facture-by-date/"+dateFrom+"/"+dateTo).pipe(map((response: Response) => response))
    }

    getFactureByCommande(idCommande){
      return this.http.get("http://localhost:8099/facture/get-facture-by-commande/"+idCommande).pipe(map((response: Response) => response))
    }

    getFacturesByUser(idUser){
      return this.http.get("http://localhost:8099/facture/get-facture-by-user/"+idUser).pipe(map((response: Response) =>response))
    }

}
