import { Component } from '@angular/core';
import { CategorieService } from 'src/app/shared/services/categorie.service';

@Component({
  selector: 'app-add-categorie',
  templateUrl: './add-categorie.component.html',
  styleUrls: ['./add-categorie.component.css']
})
export class AddCategorieComponent {
  categorie = {nomCategorie : ''};

  constructor(private categorieService: CategorieService) { }

  addCategorie() {
    this.categorieService.addCategorie(this.categorie)
      .subscribe(() => {
        // handle success
        console.log('Categorie added successfully!');
        // reset the form
        this.categorie = {nomCategorie:''};
      }, (error) => {
        // handle error
        console.log('Error adding Categorie:', error);
      });
  }

}
