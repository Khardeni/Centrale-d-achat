import { Component , OnInit , Inject} from '@angular/core';
import { impot } from '../impot';
import { ImpotService } from 'src/app/shared/services/impot.service';
import { CoreService } from 'src/app/shared/services/core.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms'


export interface fillFormDTo{
  impot : impot,
  impotId: number
}
@Component({
  selector: 'app-add-edit-impot',
  templateUrl: './add-edit-impot.component.html',
  styleUrls: ['./add-edit-impot.component.css']
})
export class AddEditImpotComponent implements OnInit{
  impForm: FormGroup;
  initialData : fillFormDTo={
    impot:{
      titreImpot:"",
      tauxImpot:0,
      isActive:false,
      impotId:0,
      typeImpot:0,},
      impotId:0
  };
  
  data : fillFormDTo;
  impotId: any;
  typeImp: number[] = [
    1,
    2,
    3,
    4,
    5,
  ];
  constructor(
    private _fb: FormBuilder,
    private impotservice : ImpotService,
    private _dialogRef: MatDialogRef<AddEditImpotComponent>,
    @Inject(MAT_DIALOG_DATA) public datad: any,
    private _coreService: CoreService
  ){
    this.impForm = this._fb.group({
      titreImpot: '',
      tauxImpot: '',
      isActive: false,
      typeImpot: '' ,
    });
  }
  ngOnInit(): void {
   /* this.impotservice.getData().subscribe(d=>{
      this.impot=d.impot;
      //this.impotId=d.impotId;
    })*/
    //console.log(this.impot);
    if(this.datad==null){
      console.log("empty");
      console.log(this.data);  
    }else{
      this.initialData=this.datad;  
    }
    this.impForm.patchValue(this.data);

    console.log(this.data);
  }
  onFormSubmit() {
    if (this.impForm.valid) {
      if (this.datad!=null) {
        this.impotservice
          .updateImpot(this.initialData.impotId,this.impForm.value)
          .subscribe({
            next: (_val: any) => {
              this._coreService.openSnackBar('impot detail updated!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });

        } else if(this.datad==null){
          this.impotservice.addImpot(this.impForm.value).subscribe({
            next: (_data: any) => {
              console.log(this.impForm.value);
              this._coreService.openSnackBar('impot added successfully');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
        }
      }
    }

}
