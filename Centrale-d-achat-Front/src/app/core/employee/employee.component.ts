import { Component } from '@angular/core';
import { EmployeeService } from 'src/app/shared/services/employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {
  listEmployee: any;
  constructor(private employeeService:EmployeeService){}

  ngOnInit(){
    document.getElementById("employee_element").classList.add('active');
    this.getList();
  }

  ngOnDestroy(){
    document.getElementById("employee_element").classList.remove('active');
  }

  getList(){
    this.employeeService.getEmployeeList().subscribe(d=> {this.listEmployee = d;})
  }

  editEmp(id:any){}

  deleteEmp(id:any){}
}
