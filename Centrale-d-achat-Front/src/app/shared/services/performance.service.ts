import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {
  constructor(private http: HttpClient) { }

  addPerformance(idEmp:any,rating:any){
    return this.http.get("http://localhost:8099/api/performance/"+idEmp+"/"+rating) .pipe(map((response: Response) => response))
  }

  getPerformanceByIdEmployee(id:any){
    return this.http.get("http://localhost:8099/api/performance/"+id) .pipe(map((response: Response) => response))
  }

  deletePerf(id:any){
    return this.http.delete("http://localhost:8099/api/performance/"+id) .pipe(map((response: Response) => response))

  }

}
