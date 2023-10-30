package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.entities.Panier;

import java.util.List;
@Repository
public interface LpanierRepository extends JpaRepository<LignePanier,Integer> {
    public List<LignePanier> findByArticle (Article article);
    public LignePanier findByArticleAndPanier(Article article, Panier panier);
    public List<LignePanier> findAllByArticleAndPanier(Article article, Panier panier);

    public void deleteAllByPanier(Panier panier);
    public List<LignePanier> findAllByOrderByQuantiteDesc();


    public List<LignePanier> findByStatus(Integer status); //houssem ktebtha kifkif
     List<LignePanier> findByPanier(Panier panier);
    public List<LignePanier> findByPanierAndStatusLessThan(Panier panier, Integer status);
     public List<LignePanier> findByPanierAndStatus(Panier panier, Integer status);
}
