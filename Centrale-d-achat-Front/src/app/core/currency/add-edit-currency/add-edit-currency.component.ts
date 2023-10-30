import { Component, OnInit , Inject} from '@angular/core';
import { CoreService } from 'src/app/shared/services/core.service';
import { CurrencyService } from 'src/app/shared/services/currency.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';
import { currency } from '../currency';


export interface fillFormDTo{
  currency : currency,
  currencyId: number
}

@Component({
  selector: 'app-add-edit-currency',
  templateUrl: './add-edit-currency.component.html',
  styleUrls: ['./add-edit-currency.component.css']
})
export class AddEditCurrencyComponent implements OnInit{
  currForm: FormGroup;
  initialData : fillFormDTo={
    currency:{
    name:"",
    active:false,
    currencyId:0,
    rate:0,
    symbol:""},
    currencyId:0
  };
  
  data : fillFormDTo;
  currencyId: any;
  Symbol: string[] = [
    'USD',
    'EUR',
    'TND',
    'RUB',
    'YEN',
  ];
  constructor(
    private _fb: FormBuilder,
    private currencyservice : CurrencyService,
    private _dialogRef: MatDialogRef<AddEditCurrencyComponent>,
    @Inject(MAT_DIALOG_DATA) public datad: any,
    private _coreService: CoreService
  ){
    this.currForm = this._fb.group({
      name: '',
      rate: '',
      active: false,
      symbol: '' ,
    });
  }
  ngOnInit(): void {
   /* this.currencyservice.getData().subscribe(d=>{
      this.currency=d.currency;
      //this.currencyId=d.currencyId;
    })*/
    //console.log(this.currency);
    if(this.datad==null){
      console.log("empty");
      console.log(this.data);  
    }else{
      this.initialData=this.datad;  
    }
    this.currForm.patchValue(this.data);

    console.log(this.data);
  }
  onFormSubmit() {
    if (this.currForm.valid) {
      if (this.datad!=null) {
        this.currencyservice
          .updateCurrency(this.initialData.currencyId,this.currForm.value)
          .subscribe({
            next: (_val: any) => {
              this._coreService.openSnackBar('currency detail updated!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });

        } else if(this.datad==null){
          this.currencyservice.addCurrency(this.currForm.value).subscribe({
            next: (_data: any) => {
              console.log(this.currForm.value);
              this._coreService.openSnackBar('currency added successfully');
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