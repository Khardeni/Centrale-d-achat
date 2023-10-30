package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.AssocArticleImpot;

import java.util.List;

public interface AssocArticleImpotRepository extends JpaRepository<AssocArticleImpot,Integer> {
    List<AssocArticleImpot> findByArticleId(Article article);
   // List<AssocArticleImpot> findByImpot (Impot impotId);


    //@Query("SELECT impot FROM AssocArticleImpot assoc JOIN assoc.impotId impot WHERE assoc.articleId.articleId = :articleId")
   // List<Impot> findByArticleId(@Param("articleId") Integer articleId);
}
