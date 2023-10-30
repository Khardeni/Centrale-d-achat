package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.ArticleEmplacement;

import java.util.List;

public interface ArticleEmplacementService {
    List<ArticleEmplacement> getAllArticleEmplacements();

    void addArticleEmplacement(ArticleEmplacement articleEmplacement, Integer articleId, Integer emplacementId);

    void updateArticleEmplacement(int id, ArticleEmplacement articleEmplacement);

    void deleteArticleEmplacement(int id);
    void checkArticleStock();
}
