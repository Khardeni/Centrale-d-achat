package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.Panier;
import tn.esprit.spring.interfaces.IArticleService;
import tn.esprit.spring.interfaces.IPanierService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/panier")
public class PanierRestController {
	@Autowired
    IPanierService iPanierService;

    @PostMapping("/createpanier/{userId}")
    public void createPanier(@PathVariable("userId") String userID) {

            iPanierService.createPanier(userID);

    }
    @DeleteMapping("/deletepanier/{idpanier}")
    public void deletelarticle(@PathVariable("idpanier") Integer idpanier){

        iPanierService.deletePanier(idpanier);
    }
    @GetMapping("/price/{minPrice}/{maxPrice}")
    public List<Article> getArticlesByPriceRange(@PathVariable Float minPrice, @PathVariable Float maxPrice) {
       // return iArticleService.getArticlesByPriceRange(minPrice, maxPrice);
        return null;
    }
    @GetMapping("/topselling/{nbr}")
    public List<Article> getTopSellingArticles(@PathVariable Integer nbr) {
        //return iArticleService.getTopSellingArticles(nbr);
        return null;
    }
    @GetMapping("/user/{userName}")
    public Panier getPanierByUser(@PathVariable("userName") String username) {
        return iPanierService.getPanierByUser(username);
    }

}


