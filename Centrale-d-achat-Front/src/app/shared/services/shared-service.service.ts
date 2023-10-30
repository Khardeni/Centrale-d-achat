import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private jwtHelper: JwtHelperService, private http:HttpClient){}
  public getToken(): string {
    return localStorage.getItem('token');
  }
  public getUsername(): string {
    return localStorage.getItem('username');
  }

  public getAnnualRevenue(year:any){
    return this.http.get("http://localhost:8099/facture/revenue/"+year) .pipe(map((response: Response) => response))
  }
  public getPendingDeliveries(){
    return this.http.get("http://localhost:8099/delivery/pending") .pipe(map((response: Response) => response))
  }
  public getLateDeliveries(){
    return this.http.get("http://localhost:8099/delivery/late") .pipe(map((response: Response) => response))
  }
}
