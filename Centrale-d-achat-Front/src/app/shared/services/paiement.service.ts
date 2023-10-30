import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { response } from 'express';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaiementService {

  constructor(private http: HttpClient) { }

  getAllPaiements(){
    return this.http.get("http://localhost:8099/billing/payments") .pipe(map((response: Response) => response))
  }

  checkout(checkoutDTO:any,username:any){
    return this.http.post("http://localhost:8099/billing/checkout-final/"+username,checkoutDTO) .pipe(map((response:Response) => response))
  }

}
