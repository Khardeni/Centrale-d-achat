package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.ArticleEmplacement;
import tn.esprit.spring.entities.ArticleRating;
import tn.esprit.spring.entities.User;

import java.util.List;

public interface ArticleRatingRepository extends JpaRepository<ArticleRating,Integer> {
    List<ArticleRating> findArticleRatingByArticle(Article article);
    @Query("SELECT ar.article FROM ArticleRating ar GROUP BY ar.article.articleId ORDER BY AVG(ar.rating) DESC")
    List<Article> findTopRatedArticlesWithLimit(int limit);
    ArticleRating findArticleRatingByUserAndArticle(User user,Article article);


}
