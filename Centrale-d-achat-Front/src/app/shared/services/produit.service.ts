import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http: HttpClient) { }

  getAllProducts(){
    return this.http.get("http://localhost:8099/article/liste-articles") .pipe(map((response: Response) => response))
  }
  addProduct(produit:Produit){
    return this.http.post("http://localhost:8099/article/addarticle/",produit) .pipe(map((response: Response) => response))
  }
  deleteProduit(idProduit: number) {
    return this.http.delete("http://localhost:8099/article/deletearticle/"+idProduit) .pipe(map((response: Response) => response))
  }
  getProduit(idProduit: number) {
    return this.http.get<Produit>("http://localhost:8099/article/get-article-by-id/" + idProduit);
  }
  updateProduit(idProduit: number, produit: any) {
    return this.http.put("http://localhost:8099/article/update/" + idProduit, produit)
      .pipe(map((response: Response) => response))
}
}
