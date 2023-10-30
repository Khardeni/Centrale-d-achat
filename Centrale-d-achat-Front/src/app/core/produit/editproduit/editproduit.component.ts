import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Produit } from 'src/app/shared/models/produit';
import { CategorieService } from 'src/app/shared/services/categorie.service';
import { MarqueService } from 'src/app/shared/services/marque.service';
import { ProduitService } from 'src/app/shared/services/produit.service';

@Component({
  selector: 'app-editproduit',
  templateUrl: './editproduit.component.html',
  styleUrls: ['./editproduit.component.css']
})
export class EditproduitComponent {

  @Input() idProduit: number;
  produit: any = new Produit();
  categories;
  firstFormGroup: FormGroup;
  marques;

  constructor(private produitService: ProduitService, private route: ActivatedRoute,private _formBuilder: FormBuilder,
    private categorieService: CategorieService,
    private marqueService: MarqueService) {
    this.produit = new Produit();
    this.route.params.subscribe(params => {
      this.idProduit = +params['id'];
    });
  }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      nomArticle: ['', Validators.required],
      prixHT: ['', Validators.required],
      seuilStock: ['', Validators.required],
      categorieArticle:['',Validators.required],
      marqueId:['',Validators.required],})
      this.categorieService.getAllCategories().subscribe(data => {
        this.categories = data;
      })
      this.marqueService.GetAllMarques().subscribe(ListMarque => {
        this.marques = ListMarque;
      })
    this.route.params.subscribe(params => {
      this.idProduit = +params['id'];
      console.log('idMarque:', this.idProduit); 
      this.produitService.getProduit(this.idProduit).subscribe(
        produit => {
          this.produit = produit;
        },
        error => {
          console.error('Error loading product', error);
        }
      );
    });
  }

  updateProduit() {
    this.produitService.updateProduit(this.idProduit, this.produit)
      .subscribe(() => console.log("product updated successfully"));
  }

}

