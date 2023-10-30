export class Produit {
  article: {
    nomArticle: string;
    seuilStock: string;
    prixHT: number;
    articleId: number;
    imagee: string;
  };
  categorieId: number;
  marqueId: number;
  
  constructor() {
    this.article = {
      nomArticle: '',
      seuilStock: '',
      prixHT: 0,
      articleId: 0,
      imagee: null
    };
    this.categorieId = 0;
    this.marqueId = 0;
  }
}