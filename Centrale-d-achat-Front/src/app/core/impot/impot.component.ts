import { Component , OnInit , ViewChild } from '@angular/core';
import { CoreService } from 'src/app/shared/services/core.service';
import { ImpotService } from 'src/app/shared/services/impot.service';
import { impot } from './impot';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { AddEditImpotComponent } from './add-edit-impot/add-edit-impot.component';

@Component({
  selector: 'app-impot',
  templateUrl: './impot.component.html',
  styleUrls: ['./impot.component.css']
})
export class ImpotComponent implements OnInit {

  impots: any = [];
  displayedColumns: string[] = [
    'titreImpot',
    'tauxImpot',
    'typeImpot',
    'isActive',
    'action',
  ];
  dataSource!: MatTableDataSource<any>;
  

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private impotservice : ImpotService, private coreService : CoreService,private _dialog: MatDialog){}

  ngOnInit(): void {
    this.getImpotList();
  }
  
  getImpotList() {
    this.impotservice.getImpotList().subscribe({
      next: (res) => {
        this.impots = res
        this.dataSource = new MatTableDataSource(this.impots);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log,
    });
  }
  
  openAddForm() {
    const dialogRef = this._dialog.open(AddEditImpotComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getImpotList();
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
  
  deleteImpot(id: any) {
    console.log(id);
    this.impotservice.deleteImpot(id.impotId).subscribe({
      next: (_res) => {
        this.coreService.openSnackBar('impot deleted!', 'done');
        this.getImpotList();
      },
      error: console.log,
    });
  }
  
  openEditForm(impotId: any, impot:impot) {
    this.impotservice.setData(impotId,impot);
    const dialogRef = this._dialog.open(AddEditImpotComponent, {
      data: { impotId, impot },
    });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getImpotList();
        }
      },
    });
   
    
  }

}
