import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { departementService } from 'src/app/shared/services/departement.service';
import { EmplacementService } from 'src/app/shared/services/emplacement.service';

export interface Emplacement{
  nomEmplacement:string,
  adresseEmplacement:string,
  gouvernorat:string,
}

@Component({
  selector: 'app-edit-emplacement',
  templateUrl: './edit-emplacement.component.html',
  styleUrls: ['./edit-emplacement.component.css']
})
export class EditEmplacementComponent {
  emp:Emplacement={
    nomEmplacement:'',
    adresseEmplacement:'',
    gouvernorat:''
  };
  idEmp: any;


  toppings = new FormControl([0]);
  dpList: any=[];
  listdp :any;
  response: any;
  depts: any=[];
  selected: any=[];
  dppList: number[]=[];
  i: number=0;

  constructor(private emplacementService:EmplacementService,private activatedRoute:ActivatedRoute, private dpService:departementService,private route:Router){}

  ngOnInit(){
    this.selected=[];
    this.dpService.getDepartementList().subscribe(d=> {this.listdp = d;
      this.dpList=d;
      });
    this.activatedRoute.params.subscribe(s => { this.idEmp=s['idEmp'] });
    this.emplacementService.getEmplacement(this.idEmp).subscribe(d => {
      this.emp.adresseEmplacement = d['adresseEmplacement'];
      this.emp.nomEmplacement = d['nomEmplacement'];
      this.emp.gouvernorat = d['gouvernorat'];
      this.emplacementService.getEmpDep(this.idEmp).subscribe(d=> {
        this.listdp=d;
        console.log(this.listdp);
        this.listdp.forEach(dpp => {

          // this.i =1;
          console.log(dpp.departmentId);
          this.dppList.push(dpp.departmentId);
          // this.i=this.i+1;
        });
        console.log(this.dppList);
        this.toppings.setValue(this.dppList);
        });
      });
  }
  filter(data){
    console.log(data.value);
  }

  updateEmplacement(){
    this.emplacementService.updateEmplacement(this.emp, this.idEmp).subscribe((response:any)=>{
      this.route.navigate["/emplacement"];
    })
    this.depts= this.toppings.value;
    this.depts.forEach(idDep => {
      this.dpService.assignEmpDep(this.idEmp,idDep).subscribe(d=> {console.log(d)},error => console.log(error))
    });
    this.route.navigate(['/emplacement'], {relativeTo: this.activatedRoute})
  }

}
