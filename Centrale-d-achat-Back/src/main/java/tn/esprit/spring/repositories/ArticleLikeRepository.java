package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.ArticleLike;
import tn.esprit.spring.entities.User;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike,Integer> {
    ArticleLike findByArticleAndUser(Article article, User user);
}
