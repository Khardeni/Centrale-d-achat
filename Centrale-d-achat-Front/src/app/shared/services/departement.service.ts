import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class departementService {
  constructor(private _http: HttpClient) {}

  addDepartement(data: any): Observable<any> {
    return this._http.post('http://localhost:8099/departement/add', data);
  }

  updateDepartement(id: any, data: any): Observable<any> {
    return this._http.put(`http://localhost:8099/departement/departments/${id}`, data);
  }

  getDepartementList(): Observable<any> {
    return this._http.get('http://localhost:8099/departement/departments');
  }

  deleteDepartement(id: any): Observable<any> {
    return this._http.delete(`http://localhost:8099/departement/departments/${id}`);
  }

  assignEmpDep(idEmp:any,idDep:any){
    return this._http.get('http://localhost:8099/empd/add/'+idEmp+"/"+idDep);
  }
}
