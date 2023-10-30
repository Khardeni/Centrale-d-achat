package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DTO.ArticleRatingDto;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IArticleRatingService;
import tn.esprit.spring.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j

public class ArticleRatingImpl implements IArticleRatingService {
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LpanierRepository lpanierRepository;
    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleRatingRepository articleRatingRepository;

 @Override

    public void addProductRating(String userID, Integer productId, int rating) {
        User user = userRepository.findByUserName(userID);
        Article article = articleRepository.findById(productId).orElse(null);
        Panier panier = panierRepository.findPanierByUserId(user);
        List<LignePanier> lignePanierList = lpanierRepository.findByPanier(panier);
     for(LignePanier lp : lignePanierList) {
         if (article.getArticleId() == lp.getArticle().getArticleId() && lp.getStatus() != 0) {
             Commande commande = commandeRepository.findById(lp.getStatus()).orElse(null);
             Facture facture = factureRepository.findByCommandeId(commande);
             Livraison livraison = deliveryRepository.findByFactureId(facture);
             if (livraison.getDateLivraison() != null) {
                 if (articleRatingRepository.findArticleRatingByUserAndArticle(user, article) == null) {
                     ArticleRating articleRating = new ArticleRating(rating, user, article);
                     articleRatingRepository.save(articleRating);
                 }
             }
         }
         if (user == null || article == null) {
             throw new NotFoundException("User or atricle not found");
         }
         //si the user has rated already the article
         //
       }
    }


    @Override
    public List<ArticleRatingDto> getArticleRatings(Integer articleId) {
       Article article= articleRepository.findById(articleId).orElse(null);


        List<ArticleRating> articleRatings = articleRatingRepository.findArticleRatingByArticle(article);

        List<ArticleRatingDto> articleRatingDtos = new ArrayList<>();
        for (ArticleRating articleRating : articleRatings) {
            ArticleRatingDto articleRatingDto = new ArticleRatingDto();
            articleRatingDto.setId(articleRating.getId());
            articleRatingDto.setArticleName(articleRating.getArticle().getNomArticle());
            articleRatingDto.setRating(articleRating.getRating());
            articleRatingDto.setUsername(articleRating.getUser().getUserFirstName()+' '+articleRating.getUser().getUserLastName());
            articleRatingDtos.add(articleRatingDto);
        }

        return articleRatingDtos;
    }
    @Override
    public double getArticleRatingAverage(Integer articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);


        List<ArticleRating> articleRatings = articleRatingRepository.findArticleRatingByArticle(article);

        if (articleRatings.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (ArticleRating articleRating : articleRatings) {
            sum += articleRating.getRating();
        }

        return sum / articleRatings.size();
    }

}
