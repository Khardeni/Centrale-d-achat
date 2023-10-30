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
  selector: 'app-add-emplacement',
  templateUrl: './add-emplacement.component.html',
  styleUrls: ['./add-emplacement.component.css']
})
export class AddEmplacementComponent {
  emp:Emplacement={
    nomEmplacement:'',
    adresseEmplacement:'',
    gouvernorat:''
  };
  listdp: any;


  toppings = new FormControl('');
  dpList: any=[];
  response: any;
  depts: any=[];


  constructor(private emplacementService:EmplacementService, private dpService:departementService,
    private route:Router, private activatedRoute:ActivatedRoute){}

  ngOnInit(){
    this.dpService.getDepartementList().subscribe(d=> {this.listdp = d;
    console.log(this.dpList);
    this.dpList= this.listdp;
    });
  }

  sendData(){
    console.log(this.emp);
    this.depts= this.toppings.value;
    this.emplacementService.saveEmplacement(this.emp).subscribe(d=> {
      this.response = d;
      this.depts.forEach(idDep => {
        this.dpService.assignEmpDep(this.response['emplacementId'],idDep).subscribe(d=> {console.log(d)},error => console.log(error))
      });
      console.log(this.response );
    },error => console.log(error));
    this.route.navigate(['/emplacement'], {relativeTo: this.activatedRoute})
  }

}
