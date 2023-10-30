package tn.esprit.spring.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Article;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public interface IArticleService {





    Article getArticleById(Integer arId);



    void deleteArticle(Integer articleId);

    List<Article> getAllArticles();

    List<Article> getAllArticlesByCategory(Integer categorieId);

    //void updateArticle(Integer articleId, String nom, Integer categorieId, Float prix, Integer stock);

    List<Article> getArticlesByPriceRange(Float minPrice, Float maxPrice);

    List<Article> getTopSellingArticles(Integer limit);


    String generateBarcodeForArticle(Integer articleId);

    void applyDiscountOnTopRatedArticles();


    void resetPromotion();

    void addArticle(Article article, Integer categorieId, Integer marqueId);

    Article uploadImage(MultipartFile file, int id) throws IOException;

    ResponseEntity<String> likeArticle(Integer idArticle, String idUser);

     Article updateArticle(Article newarticle, Integer arId);

    void addImpotArticle(Integer ImpotId, Integer ArticleId);

    void addChargeToArticle(Integer ArticleId, Integer ChargeId);
}
