import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient){}

  addEmploye(emp:any,idempdep:any) {
    return this.http.post("http://localhost:8099/employees/assign-employee/"+idempdep+"/"+emp.userName,emp).pipe(map((response : Response) => response))
  }

  getEmployeeList() {
    return this.http.get("http://localhost:8099/employees").pipe(map((response : Response) => response))
  }

  getEmployeeByED(idEmpDep: any) {
    return this.http.get("http://localhost:8099/employees/ed/"+idEmpDep) .pipe(map((response: Response) => response))
  }

  getUserList(){
    return this.http.get("http://localhost:8099/users") .pipe(map((response: Response) => response))
  }

  updateEmp(idEmp: any,emp:any) {

    console.log(emp);
    console.log(idEmp);
    return this.http.put("http://localhost:8099/employees/"+idEmp,emp) .pipe(map((response: Response) => response))
  }

  deleteEmployee(idEmp: any) {
    return this.http.delete("http://localhost:8099/employees/"+idEmp) .pipe(map((response: Response) => response))
  }

}
