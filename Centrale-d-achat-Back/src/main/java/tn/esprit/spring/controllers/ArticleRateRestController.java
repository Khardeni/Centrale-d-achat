package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DTO.ArticleRatingDto;
import tn.esprit.spring.services.ArticleRatingImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rate")
public class ArticleRateRestController {
@Autowired
    ArticleRatingImpl articleRating;
    @PostMapping("/user/{userId}/articles/{articleId}/ratings")
    public void addArticleRating(
            @PathVariable String userId,
            @PathVariable Integer articleId,
            @RequestParam int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating value must be between 1 and 5");}
        articleRating.addProductRating(userId, articleId, rating);
    }
    @GetMapping("/article/{articleId}/ratings")
    public List<ArticleRatingDto> ArticleRatings(@PathVariable Integer articleId) {
        List<ArticleRatingDto> articleRatingDtos =  articleRating.getArticleRatings(articleId);
        return articleRatingDtos;
    }

    @GetMapping("/article/{articleId}/AverageRating")
    public double ArticleRatingAVG(@PathVariable Integer articleId) {
        double avg = articleRating.getArticleRatingAverage(articleId);
        return avg;
    }


}
