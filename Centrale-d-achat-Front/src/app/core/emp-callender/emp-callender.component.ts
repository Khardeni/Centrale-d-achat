import { Component , OnInit } from '@angular/core';
import { EventSettingsModel, View } from '@syncfusion/ej2-angular-schedule';
import { DataManager, ODataV4Adaptor, Query  , UrlAdaptor} from '@syncfusion/ej2-data';
import { absence } from '../absence/absence';
import { AbsenceService } from 'src/app/shared/services/absence.service';
import * as moment from 'moment';
import { LocalDate } from 'js-joda';
import { data } from 'jquery';



@Component({
  selector: 'app-emp-callender',
  //template : `<ejs-schedule height ="650"  width = "1000"  [eventSettings]='eventSettings' [selectedDate]='selectedDate' [readonly]="readonly"> </ejs-schedule> `,
  templateUrl: './emp-callender.component.html',
  styleUrls: ['./emp-callender.component.css']
})
export class EmpCallenderComponent implements OnInit {
  eventSettings: EventSettingsModel = {
    dataSource: []
  };
  dataManager : DataManager = new DataManager({
      url : `http://localhost:8099/Absence/Get-Al-Absences`,
      adaptor: new UrlAdaptor,
      crudUrl : `http://localhost:8099/Absence/Get-Al-Absences`
  
    });
   
    dateString = '2023-05-07';
    dateMoment = moment(this.dateString, 'YYYY-MM-DD');
    dateObject = this.dateMoment.toDate();
   localDate : any ;
  constructor(private absenceservice : AbsenceService) {}
  ngOnInit() {
    
   this.localDate = LocalDate.of(
      this.dateObject.getFullYear(),
      this.dateObject.getMonth() + 1,
      this.dateObject.getDate(),
    );
    
    this.absenceservice.getAbsences().subscribe(absences => {
      this.eventSettings.dataSource = absences;
      console.log(absences)
    });
  }

 
}


