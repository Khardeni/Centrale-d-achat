import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { departementService } from 'src/app/shared/services/departement.service';

export class dep{
  departmentId:number=0;
  departmentName:string="";
}

@Component({
  selector: 'app-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.css']
})
export class DepartementComponent {
  listDepartement: any=[];
  dp:dep={
    departmentId:0,
    departmentName:""
  };
  constructor(private dpService : departementService, private route:Router, private activatedRoute:ActivatedRoute){}

  ngOnInit(){
    document.getElementById("dept_element").classList.add('active');
    this.getList();
  }

  ngOnDestroy(){
    document.getElementById("dept_element").classList.remove('active');
  }

  getList(){
    this.dpService.getDepartementList().subscribe(d=> {this.listDepartement = d;})
  }
  submitNewDp(){
    this.dpService.addDepartement(this.dp).subscribe(d=>{console.log(d);
      this.getList();},error => console.log(error))
      this.dp.departmentName="";
  }

  deletedep(id:any){
    this.dpService.deleteDepartement(id).subscribe(d=>{console.log(d);
      this.getList();},(error => {console.log(error);
      this.route.navigate(['/departement'], {relativeTo :this.activatedRoute});}));
  }

  editName(id:any){
    if((<HTMLInputElement>document.getElementById(id)).disabled== true){
     (<HTMLInputElement>document.getElementById(id)).disabled= false ;
     document.getElementById(id).classList.remove("disabled");
    }else{
      document.getElementById(id).classList.add("disabled");
      this.dp.departmentId=id;
      this.dp.departmentName=(<HTMLInputElement>document.getElementById(id)).value;
      this.dpService.updateDepartement(id,this.dp).subscribe(d=>{console.log(d);
        this.getList();},error =>
        this.getList())
      this.dp.departmentName="";
    }
  }

}
