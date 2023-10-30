package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.CategorieArticle;

public interface CategorieRepository extends JpaRepository<CategorieArticle, Integer> {

}
