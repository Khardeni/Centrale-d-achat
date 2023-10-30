package tn.esprit.spring.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IArticleService;
import tn.esprit.spring.repositories.*;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class ArticleImpl implements IArticleService{
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleRatingRepository articleRatingRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    LpanierRepository lpanierRepository;
    @Autowired
    MarqueRepository marqueRepository;
    @Autowired
    ArticleLikeRepository articleLikeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImpotRepository impotRepository;
    @Autowired
    ChargeRepository chargeRepository;
    @Autowired
    AssocArticleImpotRepository assocArticleImpotRepository;
    @Autowired
    AssocArticleChargeRepository assocArticleChargeRepository;

    @Override
    public void addArticle(Article article, Integer categorieId, Integer marqueId)  {
      CategorieArticle categorie= categorieRepository.findById(categorieId).orElse(null);
        Marque marque= marqueRepository.findById(marqueId).orElse(null);
      article.setCategorieArticle(categorie);
      article.setMarqueId(marque);
        article = articleRepository.save(article);

        log.info(article.getArticleId().toString());
        String barcode = generateBarcodeForArticle(article.getArticleId());
        article.setBarcode(barcode);
        articleRepository.save(article);






    }
    @Override
    public Article uploadImage(MultipartFile file, int id) throws IOException {
        Article article = articleRepository.findById(id).orElse(null);
        article.setImage(file.getBytes());
        return articleRepository.save(article);
    }
    @Override
    public Article getArticleById(Integer arId){
        Article article = articleRepository.findById(arId).orElse(null);
        return article;
    }
    @Autowired
    PanierRepository panierRepository;
    @Override
    public void deleteArticle(Integer articleId) {
        articleRepository.deleteById(articleId);
        /**for(Panier p:panierRepository.findAll())
        {
           for(LignePanier lignePanier :lpanierRepository.findAll())
           {
              if (lignePanier.getArticle().getArticleId()==articleId)
               {
                   lpanierRepository.deleteById(articleId);
                   lpanierRepository.save(lignePanier);
                   panierRepository.save(p);
                   p.setCoutTotal(p.getCoutTotal()-article.getPrixHT());
                   p.setNbrArticle(p.getNbrArticle()-1);


               }
           }
        }*/
    }
    @Override
    public List<Article> getAllArticles() {
        List<Article> articleList= articleRepository.findAll();
        return articleList;


    }

    @Override
    public List<Article> getAllArticlesByCategory(Integer categorieId) {
        CategorieArticle categorieArticle = categorieRepository.findById(categorieId).orElse(null);
        List<Article> articleList = articleRepository.findArticleByCategorieArticle(categorieArticle);
        return articleList;
    }
    @Override
    public Article updateArticle(Article newarticle, Integer arId) {
        Article article = articleRepository.findById(arId).orElse(null);
        article.setNomArticle(newarticle.getNomArticle());
        article.setCategorieArticle(newarticle.getCategorieArticle());
        article.setMarqueId(newarticle.getMarqueId());
        article.setPrixHT(newarticle.getPrixHT());
        article.setSeuilStock(newarticle.getSeuilStock());
        return articleRepository.save(article);
    }
    @Override
    public List<Article> getArticlesByPriceRange(Float minPrice, Float maxPrice) {
        return articleRepository.findArticleByPrixHTBetween(minPrice, maxPrice);
    }
    @Override
    public List<Article> getTopSellingArticles(Integer nbr) {
        List<LignePanier> lignePaniers = lpanierRepository.findAllByOrderByQuantiteDesc();
        List<Article> topSellingArticles = new ArrayList<>();
        int count = 0;
        for (LignePanier lp : lignePaniers) {
            if (count >= nbr) {
                break;
            }
            topSellingArticles.add(lp.getArticle());
            count++;
        }
        return topSellingArticles;
    }
    @Override
    public String generateBarcodeForArticle(Integer articleId) {
        try {
            // Generate a UUID to use as a unique identifier for this barcode
            UUID uuid = UUID.randomUUID();
            // Encode the article ID as a string
            String barcodeData = articleId.toString() + "-" + uuid;
            // Set the barcode format and encoding parameters
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 0);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            // Generate the barcode as a BitMatrix
            BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeData, BarcodeFormat.CODE_128, 100, 50, hints);

            // Convert the BitMatrix to a PNG image and encode as a Base64 string
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
            byte[] bytes = out.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            return base64;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public void applyDiscountOnTopRatedArticles() {
        float discountRate1 = 0.2f;

        float discountRate2 = 0.1f;

        List<Article> topRatedArticles = articleRatingRepository.findTopRatedArticlesWithLimit(20);
        //log.info(topRatedArticles.toString());
// Top first 10 articles 20%
        for (int i = 0; i < 10 && i < topRatedArticles.size(); i++) {
            Article article = topRatedArticles.get(i);
            float oldPrice = article.getPrixHT();
            float newPrice = oldPrice * (1 - discountRate1);
            article.setPrixHT(newPrice);
            article.setPromotion(0.2f);
            articleRepository.save(article);
        }
 //top second 10 articles 10%
        for (int i = 10; i < 20 && i < topRatedArticles.size(); i++) {
            Article article = topRatedArticles.get(i);
            float oldPrice = article.getPrixHT();
            float newPrice = oldPrice * (1 - discountRate2);
            article.setPrixHT(newPrice);
            article.setPromotion(0.1f);
            articleRepository.save(article);
        }


    }
    @Override
    public void resetPromotion() {
        List<Article> articles = articleRepository.findArticleByPromotionNotNull();
        for (Article article : articles) {
            float oldPrice = article.getPrixHT();
            float promotionRate = article.getPromotion();
            float newPrice = oldPrice / (1 - promotionRate);
            article.setPrixHT(newPrice);
            article.setPromotion(null);
            articleRepository.save(article);
        }
    }
    @Override
    public ResponseEntity<String> likeArticle(Integer idArticle, String idUser) {
        Article article = articleRepository.findById(idArticle).orElse(null);
        User user = userRepository.findByUserName(idUser);
        ArticleLike articleLike  = articleLikeRepository.findByArticleAndUser(article,user);
        if (articleLike!= null) {
            articleLikeRepository.delete(articleLike);

            article.setLikeCount(article.getLikeCount()-1);
            articleRepository.save(article);

                      return ResponseEntity.ok("Like Deleted successfuly");

        }
       else { // User has not liked the product before
            article.setLikeCount(article.getLikeCount() + 1);
            articleRepository.save(article);

            ArticleLike articleLike1 = new ArticleLike();
            articleLike1.setUser(user);
            articleLike1.setArticle(article);
            articleLikeRepository.save(articleLike1);
            return ResponseEntity.ok("Like Added To Article Successfullt");
        }
    }
    //Kol jemaa dimanche yahbtou les articles jdod en promo
   /** @Scheduled(cron = "0 0 0 * * SUN")
    public void applyDiscountScheduled() {
        applyDiscountOnTopRatedArticles();
    }
   @Scheduled(cron = "0 0 0 * * SUN") // Run every Sunday at midnight
   public void resetPromotionPricesScheduled() {
       resetPromotion();
   }**/
   @Scheduled(cron = "0 9 02 * * TUE") // Run every Sunday at midnight
   public void resetPromotionPricesScheduled() {
       resetPromotion();
       log.info("cronReset\n");
   }

    @Scheduled(cron = "0 6 02 * * TUE") // Run every Sunday at midnight
    public void apply() {
        applyDiscountOnTopRatedArticles();
        log.info("cronapply\n");
    }
    /**
    public ResponseEntity<String> updateArticle(Article article, Integer idArticle){
        Article article1= articleRepository.findById(idArticle).orElse(null);
        article1.setNomArticle(article.getNomArticle());
        article1.setMarqueId(article.getMarqueId());
        article1.setPrixHT(article.getPrixHT());
        article1.setSeuilStock(article.getSeuilStock());
        CategorieArticle categorieArticle= categorieRepository.findById(article.getCategorieArticle().getCategorieArticleId()).orElseThrow(() -> new EntityNotFoundException("categorie with id " + categorieRepository + " not found"));

        article1.setCategorieArticle(categorieArticle);

        articleRepository.save(article1);
        return ResponseEntity.ok("Article Updated Succesfuly");
    }**/





    @Override
    public void addImpotArticle(Integer ImpotId, Integer ArticleId){
        AssocArticleImpot empdep = new AssocArticleImpot();
        Impot impot = impotRepository.findById(ImpotId).orElse(null);
        Article article = articleRepository.findById(ArticleId).orElse(null);
        empdep.setImpotId(impot);
        empdep.setArticleId(article);
        assocArticleImpotRepository.save(empdep);
    }

    @Override
    public void addChargeToArticle( Integer ArticleId, Integer ChargeId){
        AssocArticleCharge emp = new AssocArticleCharge();
        Charge charge = chargeRepository.findById(ChargeId).orElse(null);
        Article article = articleRepository.findById(ArticleId).orElse(null);

        emp.setChargeId(charge);
        emp.setArticleId(article);
        assocArticleChargeRepository.save(emp);
    }

    }


