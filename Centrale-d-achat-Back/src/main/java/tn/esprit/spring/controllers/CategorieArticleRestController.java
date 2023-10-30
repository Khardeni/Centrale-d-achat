package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.CategorieArticle;
import tn.esprit.spring.entities.Marque;
import tn.esprit.spring.interfaces.IArticleService;
import tn.esprit.spring.interfaces.ICategorieService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categorie")
public class CategorieArticleRestController {
    @Autowired
    ICategorieService iCategorieService;


    @PostMapping("/addcategorie")                                            //tested
    public void addCategorie(@RequestBody CategorieArticle categorieArticle) {
        iCategorieService.addCategorie(categorieArticle);
    }

    ;

    @DeleteMapping("/deletecategorie/{idcategorie}")
    public void deleteCategorie(@PathVariable("idcategorie") Integer idCat) {

        iCategorieService.deleteCategorieById(idCat);
    }

    @GetMapping("/liste-categorie")
    public List<CategorieArticle> getCategorie() {
        return iCategorieService.findAll();
    }

    @PutMapping("/update-categorie/{idCategorie}")
    public void update(@RequestBody CategorieArticle categorie,
                       @PathVariable("idCategorie") Integer idCat) {
        iCategorieService.updateCategorie(categorie, idCat);
    }

    @GetMapping("/get-categorie-by-id/{categorieId}")                            //tested
    public CategorieArticle getMarqueById(@PathVariable("categorieId") Integer catId) {
        return iCategorieService.findCategorieById(catId);

    }
}


