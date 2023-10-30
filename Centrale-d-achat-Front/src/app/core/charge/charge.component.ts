import { Component , ViewChild , OnInit } from '@angular/core';
import { CoreService } from 'src/app/shared/services/core.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { ChargeService } from 'src/app/shared/services/charge.service';
import { AddEditChargeComponent} from './add-edit-charge/add-edit-charge.component';
import { charge } from './charge';

@Component({
  selector: 'app-charge',
  templateUrl: './charge.component.html',
  styleUrls: ['./charge.component.css']
})
export class ChargeComponent implements OnInit {
  charge : charge;
  charges: any = [];
  displayedColumns: string[] = [
    'titreCharge',
    'tauxCharge',
    'typeCharge',
    'action',
  ];
  dataSource!: MatTableDataSource<any>;
  

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private chargeService : ChargeService, private coreService : CoreService,private _dialog: MatDialog){}

  ngOnInit(): void {
    this.getChargeList();
  }
  
  getChargeList() {
    this.chargeService.getChargeList().subscribe({
      next: (res) => {
        console.log(res);
        this.charges = res
        this.dataSource = new MatTableDataSource(this.charges);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log,
    });
  }
  
  openAddForm() {
    const dialogRef = this._dialog.open(AddEditChargeComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getChargeList();
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
  
  deleteCharge(id: any) {
    console.log(id);
    this.chargeService.deleteCharge(id.chargeId).subscribe({
      next: (_res) => {
        this.coreService.openSnackBar('charge deleted!', 'done');
        this.getChargeList();
      },
      error: console.log,
    });
  }
  
  openEditForm(chargeId: any, charge: charge) {
    this.chargeService.setData(chargeId,charge);
    const dialogRef = this._dialog.open(AddEditChargeComponent, {
      data: { chargeId, charge },
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getChargeList();
        }
      },
    });
   
    
  }


}
