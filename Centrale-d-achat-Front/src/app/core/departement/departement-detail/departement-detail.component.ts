import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from 'src/app/shared/services/employee.service';
export interface Employee {
  salary: number,
  jobTitle: string,
  userName: string
}
@Component({
  selector: 'app-departement-detail',
  templateUrl: './departement-detail.component.html',
  styleUrls: ['./departement-detail.component.css']
})
export class DepartementDetailComponent {
  idEmpDep: any;
  listEmployees: any=[];
  emp:Employee={
    jobTitle:"",
    salary:0.0,
    userName:""
  };
  usersList: any=[];
  toppings = new FormControl('');
  modalChoice: any;
  idEmp: any;


  constructor(private employeeService:EmployeeService,private route:Router, private activatedRoute:ActivatedRoute){}

  ngOnInit(){
    this.modalChoice=0;
    this.employeeService.getUserList().subscribe(d=> {this.usersList = d;console.log(this.usersList)})
    this.activatedRoute.params.subscribe(s => { this.idEmpDep=s['idEmpDep'] });
    this.employeeService.getEmployeeByED(this.idEmpDep).subscribe(d=> {this.listEmployees = d;console.log(this.listEmployees);});
  }

  displayModal(){
    document.getElementById("aemodal").style.display="block";
    document.getElementById("labelusername").style.visibility="visible";
    document.getElementById("labelusername").style.maxHeight="auto";
    document.getElementById("selectusername").style.maxHeight="368px";
  }

  closeModal(){
    document.getElementById("aemodal").style.display="none";
  }

  addEmployee(){
    this.modalChoice=0;
    this.emp.userName=this.toppings.value;
    console.log(this.emp);
    this.employeeService.addEmploye(this.emp,this.idEmpDep).subscribe(d=> console.log(d));
    this.route.navigate(['/employee/de/'+this.idEmpDep],{relativeTo:this.activatedRoute});
  }

  editEmp(id:any,Emp:any){
    this.modalChoice=1;
    document.getElementById("labelusername").style.visibility="hidden";
    document.getElementById("labelusername").style.maxHeight="0px";
    document.getElementById("selectusername").style.maxHeight="0px";
    this.idEmp = id;
    this.emp.jobTitle=Emp.jobTitle;
    this.emp.salary=Emp.salary;
    document.getElementById("aemodal").style.display="block";
    console.log(this.idEmp);
    // this.employeeService.updateEmp(this.idEmp,this.emp)
  }

  deleteEmp(idEmp:any){
    this.employeeService.deleteEmployee(idEmp).subscribe(d=> console.log(d));
    this.route.navigate(['/employee/de/'+this.idEmpDep],{relativeTo:this.activatedRoute});
  }

  updateEmp(emp:any){
    this.employeeService.updateEmp(this.idEmp,emp).subscribe(d=> console.log(d));
    this.route.navigate(['/employee/de/'+this.idEmpDep],{relativeTo:this.activatedRoute});
  }

}
