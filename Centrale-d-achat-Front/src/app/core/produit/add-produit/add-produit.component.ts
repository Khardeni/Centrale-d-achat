import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Produit } from 'src/app/shared/models/produit';
import { ProduitService } from 'src/app/shared/services/produit.service';
import { CategorieService } from 'src/app/shared/services/categorie.service';
import { Categorie } from 'src/app/shared/models/categorie';
import { MarqueService } from 'src/app/shared/services/marque.service';



@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
  styleUrls: ['./add-produit.component.css']
})
export class AddProduitComponent {
  produit: Produit = new Produit();
  categories;
  firstFormGroup: FormGroup;
  marques;

  constructor(
    private _formBuilder: FormBuilder,
    private produitService: ProduitService,
    private categorieService: CategorieService,
    private marqueService: MarqueService
  ) {}

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      nomArticle: ['', Validators.required],
      prixHT: ['', Validators.required],
      seuilStock: ['', Validators.required],
      categorieArticle:['',Validators.required],
      marqueId:['',Validators.required],
    });
    this.categorieService.getAllCategories().subscribe(data => {
      this.categories = data;
    })
    this.marqueService.GetAllMarques().subscribe(ListMarque => {
      this.marques = ListMarque;
    })
    
    
  }

  checkSelect() {
    console.log(this.firstFormGroup.value);
    this.produit.article.nomArticle = this.firstFormGroup.value.nomArticle;
    this.produit.article.prixHT = this.firstFormGroup.value.prixHT;
    this.produit.article.seuilStock = this.firstFormGroup.value.seuilStock;
    this.produit.categorieId=this.firstFormGroup.value.categorieArticle;
    this.produit.marqueId=this.firstFormGroup.value.marqueId;
  }
  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.produit.article.imagee = reader.result as string;
    };
  }

    AddProduct() {
      this.produitService.addProduct(this.produit).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        }
      );
    }
}
