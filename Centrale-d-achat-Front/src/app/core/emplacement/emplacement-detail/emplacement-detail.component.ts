import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmplacementService } from 'src/app/shared/services/emplacement.service';

@Component({
  selector: 'app-emplacement-detail',
  templateUrl: './emplacement-detail.component.html',
  styleUrls: ['./emplacement-detail.component.css']
})
export class EmplacementDetailComponent {
  idEmp: any;
  listDepts: any=[];

  constructor(private emplacementService:EmplacementService,private activatedRoute:ActivatedRoute){}

  ngOnInit(){
    this.activatedRoute.params.subscribe(s => { this.idEmp=s['idEmp'] });
    this.emplacementService.getDetailEmpDTO(this.idEmp).subscribe(d=> {this.listDepts = d;console.log(this.listDepts);});
  }

}
