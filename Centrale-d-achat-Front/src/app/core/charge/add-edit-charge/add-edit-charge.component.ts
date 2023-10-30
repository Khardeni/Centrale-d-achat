import { Component , OnInit , Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ChargeService } from 'src/app/shared/services/charge.service';
import { CoreService } from 'src/app/shared/services/core.service';
import { charge } from '../charge';

export interface FormDto{
  charge : charge,
  chargeId: number
}

@Component({
  selector: 'app-add-edit-charge',
  templateUrl: './add-edit-charge.component.html',
  styleUrls: ['./add-edit-charge.component.css']
})
export class AddEditChargeComponent implements OnInit{
  currForm: FormGroup;
  initialData : FormDto={
    charge:{
    chargeId:0,
    titreCharge:"",
    tauxCharge:0,
    typeCharge:""},
    chargeId:0
  };
  
  data : FormDto;
  chargeId: any;
  typeCharge: String[] = [
    "Menstruel",
    "semestriel",
    "annuel"

  ];
  constructor(
    private _fb: FormBuilder,
    private chargeservice : ChargeService,
    private _dialogRef: MatDialogRef<AddEditChargeComponent>,
    @Inject(MAT_DIALOG_DATA) public datad: any,
    private _coreService: CoreService
  ){
    this.currForm = this._fb.group({
      chargeId: '',
      titreCharge: '',
      tauxCharge: '',
      typeCharge: '' ,
    });
  }
  ngOnInit(): void {
   /* this.currencyservice.getData().subscribe(d=>{
      this.currency=d.currency;
      //this.currencyId=d.currencyId;
    })*/
    //console.log(this.currency);
    if(this.datad==null){
     // console.log("empty");
     // console.log(this.data);  
    }else{
      this.initialData=this.datad;  
    }
    this.currForm.patchValue(this.data);

   // console.log(this.data);
  }
  onFormSubmit() {
    if (this.currForm.valid) {
      if (this.datad!=null) {
        this.chargeservice
          .updateCharge(this.initialData.chargeId,this.currForm.value)
          .subscribe({
            next: (_val: any) => {
              this._coreService.openSnackBar('charge detail updated!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });

        } else if(this.datad==null){
          this.chargeservice.addCharge(this.currForm.value).subscribe({
            next: (_data: any) => {
              console.log(this.currForm.value);
              this._coreService.openSnackBar('charge added successfully');
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
