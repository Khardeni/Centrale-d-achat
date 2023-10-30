import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { absence } from 'src/app/core/absence/absence';
import { Observable } from 'rxjs';
import { DataManager, ReturnOption, UrlAdaptor } from '@syncfusion/ej2-data';



@Injectable({
  providedIn: 'root'
})
export class AbsenceService {

  constructor(private http: HttpClient) { }
  
  getAbsences(): Observable<absence[]>{
    return this.http.get<absence[]>("http://localhost:8099/Absence/Get-All-Absences") 
  }
  
  addAbsence(absence:absence , employeId: number) {
    return this.http.post(`http://localhost:8099/Absence/add-Absence/${employeId}`, absence);
  }
  
  updateAbsence(absenceId : any , absence: absence) {
    return this.http.put<void>(`http://localhost:8099/Absence/Update-Absence/${absenceId}`, absence);
  }
  
  deleteAbsence(id: number): Observable<void> {
    return this.http.delete<void>(`http://localhost:8099/Absence/delete-Absence/${id}`);
  }
  
  approveAbsence(id: number, absence : absence): Observable<absence> {
    return this.http.post<absence>(`http://localhost:8099/Absence/approuve-absence/${id}`,absence);
  }
  
  rejectAbsence(id: number , abs : absence): Observable<absence> {
    return this.http.post<absence>(`http://localhost:8099/Absence/reject-absence/${id}`, abs);
  }
}
