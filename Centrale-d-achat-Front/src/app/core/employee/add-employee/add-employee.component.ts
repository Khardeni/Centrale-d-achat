import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { departementService } from 'src/app/shared/services/departement.service';
import { EmplacementService } from 'src/app/shared/services/emplacement.service';
export interface Employee {
  salary: 0,
  jobTitle: "",
  totalWorkedHours: 0
}
export interface SelectC{
  value:0,
  viewValue:''
}
@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {
  emp:Employee={
    jobTitle:"",
    salary:0.0,
    totalWorkedHours:0
  };
  idEmp:any;
  idDep:any;
  constructor(private activatedRoute:ActivatedRoute,private emplacementService:EmplacementService){}
  ngOnInit(){
    this.activatedRoute.params.subscribe(s => { this.idEmp=s['idEmp'] });
    this.activatedRoute.params.subscribe(s => { this.idDep=s['idDep'] });
    console.log(this.idDep+"//"+this.idEmp);
  }

  sendData(){
    
  }
}
