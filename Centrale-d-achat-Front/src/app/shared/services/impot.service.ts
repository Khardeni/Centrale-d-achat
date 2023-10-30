import { Injectable } from '@angular/core';
import { impot } from 'src/app/core/impot/impot';
import {HttpClient, HttpResponse, HttpHeaders, HttpRequest} from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ImpotService {

  impot:impot;
  impotId=0;
  any:any;
  setData(id: any, impot: impot) {
    this.impotId=id;
    this.impot=impot;
  }
  getData() {
    this.any.id=this.impotId;
    this.any.Impot=this.impot;
    return this.any;
  }
  

  constructor(private http: HttpClient){ }

  addImpot(Impot:any){
     return this.http.post("http://localhost:8099/Impot/add-Impot/",Impot) .pipe(map((response: Response) => response))
   }
   getImpotList(){
     return this.http.get("http://localhost:8099/Impot/Get-All-Impots") .pipe(map((response: Response) => response))
   }
   deleteImpot(ImpotId:any){
     return this.http.delete("http://localhost:8099/Impot/delete-Impot/"+ImpotId) .pipe(map((response: Response) => response))
   }
   updateImpot(ImpotId: any, updatedImpot: impot) {
     return this.http.put(`http://localhost:8099/Impot/Update-Impot/${ImpotId}`, updatedImpot);
   }
}
