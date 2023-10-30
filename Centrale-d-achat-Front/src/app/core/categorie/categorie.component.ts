import { Component } from '@angular/core';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { Categorie } from 'src/app/shared/models/categorie';
import { CategorieService } from 'src/app/shared/services/categorie.service';

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent {
  listCategorie:any=[];
  categorieToDelete:Categorie;
  plusIcon = faPlusCircle;
  selectedCategorie: Categorie;


  constructor(private categorieService: CategorieService) {   }

  ngOnInit(){
    this.categorieService.getAllCategories().subscribe(Catlist => this.listCategorie = Catlist);
  }

  deleteCategorie(categorie: Categorie) {
    this.categorieToDelete = categorie;
    this.categorieService.deleteCategorie(categorie.categorieArticleId)
      .subscribe(() => {
        console.log('Marque deleted successfully');
        this.listCategorie = this.listCategorie.filter(m => m.categorieArticleId !== categorie.categorieArticleId);
      }, error => {
        console.error('Error deleting Marque', error);
      });
  }
  selectCategorie(categorie: Categorie) {
    this.selectedCategorie = categorie;
  }

}
