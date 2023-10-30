import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { response } from 'express';
import { error } from 'jquery';
import { map } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class HealthService {

  constructor(private http: HttpClient) {
  }


  getAvailableBackups(){
    return this.http.get("http://localhost:8099/backups/show") .pipe(map((response: Response) => response))
  }

  deleteDatabase(){
    return this.http.get("http://localhost:8099/backups/delete") .pipe(map((response: Response) => response))
  }

  updateDatabase(filename:any){
    return this.http.get("http://localhost:8099/backups/restore/"+filename) .pipe(map((response: Response) => response))
  }

  getFactureHealth(){
    return this.http.get("http://localhost:8099/backups/health/facture") .pipe(map((response: Response) => response))
  }

  getReplication() {
    return this.http.get("http://localhost:8099/backups/health/replication") .pipe(map((response: Response) => response))
  }

  addError(type:any,request:any){
    return this.http.get("http://localhost:8099/backups/health/error/g/"+type+"/"+request)
  }

  getErrors(type:any){
    return this.http.get("http://localhost:8099/backups/health/request/"+type) .pipe(map((response: Response) => response))
  }

  getLogs(type:any){
    return this.http.get("http://localhost:8099/backups/health/error/log/"+type) .pipe(map((response: Response) => response))
  }


  generate404() {
    return this.http.get("http://localhost:8099/error/iwanta404error") .pipe(map((response: Response) => response))
  }

  generate401() {
    return this.http.get("http://localhost:8099/error/iwanta404error") .pipe(map((response: Response) => response))
  }

  generate500() {
    return this.http.get("http://localhost:8099/delivery/set-shipped/-222") .pipe(map((response: Response) => response))
  }

  generatex() {
    return this.http.get("http://localhost:8099/error/iwanta404error") .pipe(map((response: Response) => response))
  }

}
