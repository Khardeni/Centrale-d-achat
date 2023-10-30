import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import {HttpClient, HttpResponse, HttpHeaders, HttpRequest} from '@angular/common/http';
import { charge } from 'src/app/core/charge/charge';



@Injectable({
  providedIn: 'root'
})
export class ChargeService {
  chargeId=0;
  charge:charge;
  any:any;
  
  setData(id: any, charge: charge) {
    this.chargeId=id;
    this.charge=charge;
  }
  getData() {
    this.any.id=this.chargeId;
    this.any.charge=this.charge;
    return this.any;
  }
  

  constructor(private http: HttpClient){ }

  addCharge(Charge:any){
     return this.http.post("http://localhost:8099/Charge/add-Charge/",Charge) .pipe(map((response: Response) => response))
   }
   getChargeList(){
     return this.http.get("http://localhost:8099/Charge/Get-All-Charges") .pipe(map((response: Response) => response))
   }
   deleteCharge(ChargeId:any){
     return this.http.delete("http://localhost:8099/Charge/delete-Charge/"+ChargeId) .pipe(map((response: Response) => response))
   }
   updateCharge(ChargeId: any, updatedCharge: charge) {
     return this.http.put(`http://localhost:8099/Charge/Update-Charge/${ChargeId}`, updatedCharge);
   }
}
