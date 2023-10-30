package tn.esprit.spring.interfaces;

import tn.esprit.spring.DTO.ArticleRatingDto;

import java.util.List;

public interface IArticleRatingService {
    void addProductRating(String userID, Integer productId, int rating);

    List<ArticleRatingDto> getArticleRatings(Integer articleId);

    double getArticleRatingAverage(Integer articleId);

}
