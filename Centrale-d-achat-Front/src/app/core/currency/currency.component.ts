import { Component, ViewChild , OnInit } from '@angular/core';
import { CurrencyService } from 'src/app/shared/services/currency.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { currency } from './currency';
import { CoreService } from 'src/app/shared/services/core.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { AddEditCurrencyComponent } from './add-edit-currency/add-edit-currency.component';
import { data, event } from 'jquery';

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.css']
})



export class CurrencyComponent implements OnInit {
  

  currencies: any = [];
  displayedColumns: string[] = [
    'name',
    'rate',
    'active',
    'symbol',
    'action',
  ];

  
  dataSource!: MatTableDataSource<any>;
  

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private currencyService : CurrencyService, private coreService : CoreService,private _dialog: MatDialog){}

  ngOnInit(): void {
    this.getCurrencyList();
  }
  
  getCurrencyList() {
    this.currencyService.getCurrencyList().subscribe({
      next: (res) => {
        this.currencies = res
        this.dataSource = new MatTableDataSource(this.currencies);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log,
    });
  }
  
  openAddForm() {
    const dialogRef = this._dialog.open(AddEditCurrencyComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getCurrencyList();
        }
      },
    });
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  
  deleteCurrency(id: any) {
    console.log(id);
    this.currencyService.deleteCurrency(id.currencyId).subscribe({
      next: (_res) => {
        this.coreService.openSnackBar('currency deleted!', 'done');
        this.getCurrencyList();
      },
      error: console.log,
    });
  }
  
  openEditForm(currencyId: any, currency:currency) {
    this.currencyService.setData(currencyId,currency);
    const dialogRef = this._dialog.open(AddEditCurrencyComponent, {
      data: { currencyId, currency },
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getCurrencyList();
        }
      },
    });
   
    
  }
}



