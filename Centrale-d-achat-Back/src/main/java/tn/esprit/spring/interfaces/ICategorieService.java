package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.CategorieArticle;

import java.util.List;

public interface ICategorieService {
    void addCategorie (CategorieArticle categorie);

    CategorieArticle findCategorieById(Integer categorieId);


    void deleteCategorieById(Integer categorieId);



    CategorieArticle updateCategorie(CategorieArticle newcategorie, Integer catId);

    List<CategorieArticle> findAll();
}
