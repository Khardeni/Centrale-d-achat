import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Marque } from '../models/marque';
import { map } from 'rxjs';
import { response } from 'express';


@Injectable({
  providedIn: 'root'
})
export class MarqueService {

  
  constructor(private http: HttpClient) { }

    
  addMarque(marque:Marque){
    return this.http.post("http://localhost:8099/marque/addmarque",marque) .pipe(map((response: Response) => response))
  }
  GetAllMarques(){
    return this.http.get("http://localhost:8099/marque/liste-marque") .pipe(map((response: Response) => response))
  }
  deleteMarque(idMarque: number) {
    return this.http.delete("http://localhost:8099/marque/delete-marque/"+idMarque) .pipe(map((response: Response) => response))

  }
  updateMarque(idMarque: number, marque: Marque) {
    return this.http.put("http://localhost:8099/marque/update-marque/" + idMarque, marque)
      .pipe(map((response: Response) => response))
  }
  getMarque(idMarque: number) {
    return this.http.get<Marque>("http://localhost:8099/marque/get-marque-by-id/" + idMarque);
  }
  

}
