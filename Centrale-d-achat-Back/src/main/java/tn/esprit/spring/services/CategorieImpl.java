package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.CategorieArticle;
import tn.esprit.spring.interfaces.ICategorieService;
import tn.esprit.spring.repositories.ArticleRepository;
import tn.esprit.spring.repositories.CategorieRepository;

import java.util.List;

@Service
@Slf4j
public class CategorieImpl implements ICategorieService {
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void addCategorie(CategorieArticle categorie) {

        categorieRepository.save(categorie);
    }
    @Override

    public CategorieArticle findCategorieById(Integer categorieId){
        CategorieArticle categorie= categorieRepository.findById(categorieId).orElse(null);
        return categorie;
    }

    @Override

    public void deleteCategorieById(Integer categorieId){

        categorieRepository.deleteById(categorieId);
    }
    @Override

    public CategorieArticle updateCategorie(CategorieArticle newcategorie, Integer catId) {
        CategorieArticle categorie = categorieRepository.findById(catId).orElse(null);
        categorie.setNomCategorie(newcategorie.getNomCategorie());
        return categorieRepository.save(categorie); }
    @Override

    public List<CategorieArticle> findAll(){
        List<CategorieArticle> categorieArticleList= categorieRepository.findAll();
        return categorieArticleList;
    }



}
