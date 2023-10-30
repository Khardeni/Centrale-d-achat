package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.CategorieArticle;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
 List<Article> findArticleByCategorieArticle(CategorieArticle categorieArticle);
  List<Article> findArticleByPrixHTBetween( Float startPrixHT,Float endPrixHT);
 Article findArticleByNomArticleAndAndPrixHTAndSeuilStock(String nom,Integer prix,Integer stock);
List<Article> findArticleByPromotionNotNull();}

