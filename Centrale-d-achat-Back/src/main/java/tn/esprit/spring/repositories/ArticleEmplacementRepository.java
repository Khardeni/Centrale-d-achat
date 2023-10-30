package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.ArticleEmplacement;
import tn.esprit.spring.entities.Emplacement;

import java.util.List;

@Repository
public interface ArticleEmplacementRepository extends JpaRepository<ArticleEmplacement, Long> {
    public ArticleEmplacement findByArticleAndEmplacement(Article article, Emplacement emplacement);
    public List<ArticleEmplacement> findByEmplacement(Emplacement emplacement);
    public ArticleEmplacement findByArticle(Article article);
}
