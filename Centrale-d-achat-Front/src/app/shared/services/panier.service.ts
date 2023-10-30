import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PanierService {

  constructor( private http: HttpClient ) { }
  createPanier(userId:string){
    return this.http.post("http://localhost:8099/panier/createpanier",+userId) .pipe(map((response: Response) => response))
  }

  getPanierByUser(userId:string){
    return this.http.get("http://localhost:8099/panier/user/"+userId) .pipe(map((response: Response) => response))
  }

  addLP(lpDTO:any){
    return this.http.post("http://localhost:8099/lignep/addlp/",lpDTO) .pipe(map((response: Response) => response))
  }

  getLPbyUser(username: any) {
    return this.http.get("http://localhost:8099/lignep/cart/"+username) .pipe(map((response: Response) => response))
  }

  getCheckoutDetailsForUser(username: any) {
    return this.http.get("http://localhost:8099/lignep/cart/val/"+username) .pipe(map((response: Response) => response))
  }

  updateStatus(idc: any) {
    return this.http.get("http://localhost:8099/lignep/update-status/"+idc) .pipe(map((response: Response) => response))
  }

}
