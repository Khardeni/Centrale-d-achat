package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.AssocArticleCharge;

import java.util.List;
@Repository
public interface AssocArticleChargeRepository extends JpaRepository<AssocArticleCharge,Integer> {
    List<AssocArticleCharge> findByArticleId(Article article);

    //  @Query("SELECT Charge FROM AssocArticleCharge assoc INNER JOIN assoc.chargeId WHERE assoc.articleId.articleId = :articleId")
 //  List<Charge> findByArticleId(@Param("articleId") Integer articleId);
}
