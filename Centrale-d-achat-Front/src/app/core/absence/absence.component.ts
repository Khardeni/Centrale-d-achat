import { Component, OnInit } from '@angular/core';
import { absence } from './absence';
import { AbsenceService } from 'src/app/shared/services/absence.service';
import { employee } from './employee';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmpCallenderComponent } from '../emp-callender/emp-callender.component';
import {EventSettingsModel, View, EventRenderedArgs, DayService, WeekService, WorkWeekService,
  MonthService, AgendaService, ResizeService, DragAndDropService
} from '@syncfusion/ej2-angular-schedule';
import { extend } from '@syncfusion/ej2-base';


export interface fillFormDTo{
  employee : employee,
  employeeId : number
}

@Component({
  selector: 'app-absence',
  templateUrl: './absence.component.html',
  styleUrls: ['./absence.component.css']
  
})
export class AbsenceComponent implements OnInit {

  calendar : any;
  initialData : fillFormDTo={
    employee:{
    employeeId:0,
    jobTitle:"",
    salary :0,},
    employeeId:0
  };
  dataF : fillFormDTo;
  employeeId: any;
  absences: any[] = [];
  employees : employee [] ; 
  employe : employee
  absence : absence

  constructor(private absenceservice: AbsenceService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.absenceservice.getAbsences().subscribe(d => {
      this.absences=d;
    })
  }
  
  getAbsenceList(){
  this.absenceservice.getAbsences().subscribe(res => {
    this.absences= res;
  });
  }

  updateAbsence(absenceId : any , absence: absence): void {
    this.absenceservice.updateAbsence(absenceId,absence).subscribe(() => {
      this.snackBar.open('Absence updated successfully', 'OK', {
        duration: 3000 
      });
    });
  }

  deleteAbsence(id: number): void {
    this.absenceservice.deleteAbsence(id).subscribe(() => {
      this.absences = this.absences.filter(a => a.absenceId !== id);
    });
  }

  approveAbsence(id: number, absence:absence): void {
    this.absenceservice.approveAbsence(id, absence).subscribe(approvedAbsence => {
      const index = this.absences.findIndex(a => a.absenceId === id);
      this.absences[index] = approvedAbsence;
    });
  }
  rejectAbsence(id: number, abs : absence): void {
    this.absenceservice.rejectAbsence(id, abs).subscribe(rejectedAbsence => {
      const index = this.absences.findIndex(a => a.absenceId === id);
      this.absences[index] = rejectedAbsence;
    }
  );
  }
}
