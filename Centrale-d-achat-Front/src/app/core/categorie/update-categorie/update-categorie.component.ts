import { Component,Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Categorie } from 'src/app/shared/models/categorie';
import { CategorieService } from 'src/app/shared/services/categorie.service';

@Component({
  selector: 'app-update-categorie',
  templateUrl: './update-categorie.component.html',
  styleUrls: ['./update-categorie.component.css']
})
export class UpdateCategorieComponent {
  @Input() idCategorie: number;
  categorie: Categorie = new Categorie();

  constructor(private categorieService: CategorieService, private route: ActivatedRoute) {
    this.categorie = new Categorie();
    this.route.params.subscribe(params => {
      this.idCategorie = +params['id'];
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idCategorie = +params['id'];
      console.log('idCategorie:', this.idCategorie); 
      this.categorieService.getCategorie(this.idCategorie).subscribe(
        categorie => {
          this.categorie = categorie;
        },
        error => {
          console.error('Error loading marque', error);
        }
      );
    });
  }

  updateCategorie() {
    this.categorieService.updateCategorie(this.idCategorie, this.categorie)
      .subscribe(() => console.log("Categorie updated successfully"));
  }

}


