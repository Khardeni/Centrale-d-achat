import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse, HttpHeaders, HttpRequest} from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { response } from 'express';
import { currency } from 'src/app/core/currency/currency';


@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  currencyId=0;
  currency:currency;
  any:any;
  setData(currencyId: any, currency: currency) {
    this.currencyId=currencyId;
    this.currency=currency;
  }


  getData() {
    this.any.currencyId=this.currencyId;
    this.any.currency=this.currency;
    return this.any;
  }

  constructor(private http: HttpClient){ }

 addCurrency(currency:any){
    return this.http.post("http://localhost:8099/Currency/add-Currency/",currency) .pipe(map((response: Response) => response))
  }
  getCurrencyList(){
    return this.http.get("http://localhost:8099/Currency/Get-All-Currencies") .pipe(map((response: Response) => response))
  }
  deleteCurrency(currencyId:any){
    return this.http.delete("http://localhost:8099/Currency/delete-Currency/"+currencyId) .pipe(map((response: Response) => response))
  }
  updateCurrency(currencyId: any, updatedCurrency: currency) {
    return this.http.put(`http://localhost:8099/Currency/Update-Currency/${currencyId}`, updatedCurrency);
  }
  getExchangeRate(from:any,to:any){
    return this.http.get("http://localhost:8099/Currency/ExchangeRate/"+from+"/"+to+"?Amount="+1) .pipe(map((response: Response) => response))

  }
}
