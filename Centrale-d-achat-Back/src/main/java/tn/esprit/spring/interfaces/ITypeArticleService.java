package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.TypeArticle;

public interface ITypeArticleService {
    void AddTypeArticle (TypeArticle typeArticle);

    void DeleteTypeArticle(TypeArticle typeArticle);
}
