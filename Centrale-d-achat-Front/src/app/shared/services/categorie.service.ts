import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Categorie } from '../models/categorie';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http: HttpClient) { }

  getAllCategories(){
    return this.http.get("http://localhost:8099/categorie/liste-categorie") .pipe(map((response: Response) => response))
  }
  addCategorie(categorie:Categorie){
    return this.http.post("http://localhost:8099/categorie/addcategorie",categorie) .pipe(map((response: Response) => response))
  }
  deleteCategorie(idCategorie: number) {
    return this.http.delete("http://localhost:8099/categorie/deletecategorie/"+idCategorie) .pipe(map((response: Response) => response))
  }
  getCategorie(idCategorie: number) {
    return this.http.get<Categorie>("http://localhost:8099/categorie/get-categorie-by-id/" + idCategorie);
  }
  updateCategorie(idCategorie: number, categorie: Categorie) {
    return this.http.put("http://localhost:8099/categorie/update-categorie/" + idCategorie, categorie)
      .pipe(map((response: Response) => response))
  }
}
