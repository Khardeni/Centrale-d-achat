import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Emplacement } from 'src/app/core/emplacement/add-emplacement/add-emplacement.component';

@Injectable({
  providedIn: 'root'
})
export class EmplacementService {

  constructor(private http: HttpClient) { }

  getAllEmplacements(){
    return this.http.get("http://localhost:8099/emplacements/getall") .pipe(map((response: Response) => response))
  }

  getEmplacement(idEmp:any){
    return this.http.get("http://localhost:8099/emplacements/"+idEmp) .pipe(map((response: Response) => response))
  }

  saveEmplacement(emplacement:any){
    return this.http.post("http://localhost:8099/emplacements/add/",emplacement) .pipe(map((response: Response) => response))
  }

  deleteEmplacement(idEmplacement:any){
    return this.http.delete("http://localhost:8099/emplacements/"+idEmplacement) .pipe(map((response: Response) => response))
  }
  updateEmplacement(emplacement: any, idemp: any){
    return this.http.put("http://localhost:8099/emplacements/" + idemp, emplacement)
    .pipe(map((response: any) => response));
    }

  getEmpDep(idEmp:any){
    return this.http.get("http://localhost:8099/empd/"+idEmp) .pipe(map((response: Response) => response))
  }

  getDetailEmpDTO(idEmp: any) {
    return this.http.get("http://localhost:8099/emplacements/detail/"+idEmp) .pipe(map((response: Response) => response))
  }


}
