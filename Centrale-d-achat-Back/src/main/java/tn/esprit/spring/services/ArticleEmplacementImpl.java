package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.ArticleEmplacement;
import tn.esprit.spring.interfaces.ArticleEmplacementService;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.entities.Emplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ArticleEmplacementImpl implements ArticleEmplacementService {

    @Autowired
    private ArticleEmplacementRepository articleEmplacementRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private EmplacementRepository emplacementRepository;

    @Override
    public List<ArticleEmplacement> getAllArticleEmplacements() {
        List<ArticleEmplacement> articleEmplacements = new ArrayList<>();
        articleEmplacementRepository.findAll().forEach(articleEmplacements::add);
        return articleEmplacements;
    }

    @Override
    public void addArticleEmplacement(ArticleEmplacement articleEmplacement, Integer articleId, Integer emplacementId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        Emplacement emplacement = emplacementRepository.findById(emplacementId).orElse(null);
        ArticleEmplacement articleEmplacement1 = articleEmplacementRepository.findByArticleAndEmplacement(article, emplacement);
        Integer stock = articleEmplacement.getStockE();
        ArticleEmplacement ae = new ArticleEmplacement();
          /*  System.out.println(articleEmplacement1);
            System.out.println("fefe");*/
        if (articleEmplacement1 == null) {
            ae.setStockE(stock);
            ae.setEmplacement(emplacement);
            ae.setArticle(article);
            articleEmplacementRepository.save(ae);
        } else {
            articleEmplacement1.setStockE(articleEmplacement1.getStockE() + articleEmplacement.getStockE());
            articleEmplacementRepository.save(articleEmplacement1);
        }
    }

    @Override
    public void updateArticleEmplacement(int id, ArticleEmplacement articleEmplacement) {
        Optional<ArticleEmplacement> optionalArticleEmplacement = articleEmplacementRepository.findById((long) id);
        if (optionalArticleEmplacement.isPresent()) {
            ArticleEmplacement existingArticleEmplacement = optionalArticleEmplacement.get();
            existingArticleEmplacement.setStockE(articleEmplacement.getStockE());
            articleEmplacementRepository.save(existingArticleEmplacement);
        }
    }

    @Override
    public void deleteArticleEmplacement(int id) {
        articleEmplacementRepository.deleteById((long) id);
    }

   //* @Scheduled(cron = "*/5 * * * * *")
    @Override
    public void checkArticleStock() {
        List<ArticleEmplacement> articleEmplacementList = articleEmplacementRepository.findAll();
        for (ArticleEmplacement ae : articleEmplacementList) {
            Article article = articleRepository.findById(ae.getArticle().getArticleId()).orElse(null);
            if (ae.getStockE() < article.getSeuilStock()) {
                log.info("L'article N:" + article.getArticleId() + " - " + article.getNomArticle() + "  Est inférieur au seuil Stock");
            }
        }
    }
}