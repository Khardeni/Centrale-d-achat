package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.DTO.ArticleDto;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IArticleService;
import tn.esprit.spring.interfaces.ILpanierService;
import tn.esprit.spring.repositories.ArticleRepository;
import tn.esprit.spring.repositories.AssocArticleChargeRepository;
import tn.esprit.spring.repositories.AssocArticleImpotRepository;
import tn.esprit.spring.services.NotFoundException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleRestController {
    @Autowired
    IArticleService iArticleService;
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    AssocArticleImpotRepository assocArticleImpot;

    @Autowired
    AssocArticleChargeRepository assocArticleChargeRepository;


    @PostMapping("/addarticle")
    public void addArticle(@RequestBody ArticleDto articleDto) {
        iArticleService.addArticle(articleDto.getArticle(),articleDto.getCategorieId(),articleDto.getMarqueId());
    }

    @GetMapping("/get-article-by-id/{articleId}")                            //tested
    public Article getArticle(@PathVariable("articleId") Integer articleId) {
        return iArticleService.getArticleById(articleId);
    }
    @PutMapping  ("/update/{articleId}")
    public Article update(@RequestBody Article article, @PathVariable("articleId") Integer articleId) {
        return iArticleService.updateArticle(article, articleId);
    }
    @GetMapping("/liste-articles")
    public List<Article> getArticles() {
        return iArticleService.getAllArticles();
    }

    @GetMapping("/get-article-by-categorie/{categorieId}")                            //tested
    public List<Article> getFactureByUser(@PathVariable("categorieId") Integer categoryId) {
        return iArticleService.getAllArticlesByCategory(categoryId);
    }

    @DeleteMapping("/deletearticle/{idarticle}")
    public void deletelarticle(@PathVariable("idarticle") Integer idArticle) {

        iArticleService.deleteArticle(idArticle);
    }

    @GetMapping("/price/{minPrice}/{maxPrice}")
    public List<Article> getArticlesByPriceRange(@PathVariable Float minPrice, @PathVariable Float maxPrice) {
        return iArticleService.getArticlesByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/topselling/{nbr}")
    public List<Article> getTopSellingArticles(@PathVariable Integer nbr) {
        return iArticleService.getTopSellingArticles(nbr);
    }

    @GetMapping(value = "/{articleId}/barcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBarcodeForArticle(@PathVariable Integer articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        String barcode = article.getBarcode();
        if (barcode == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Base64.getDecoder().decode(barcode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

    }
    @PostMapping("/apply-discount")
    public ResponseEntity<String> applyDiscountOnTopRatedArticles() {
        try {
            iArticleService.applyDiscountOnTopRatedArticles();
            return ResponseEntity.ok("Discounts applied successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error applying discounts");
        }
    }
    @PostMapping("/reset-discount")
    public ResponseEntity<String> ResetDiscountOnTopRatedArticles() {
        try {
            iArticleService.resetPromotion();
            return ResponseEntity.ok("Reset discounts applied successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error applying discounts");
        }
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        try {
             iArticleService.uploadImage(file, id);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }
    @PutMapping("/like/{idArticle}/{idUser}")
    public ResponseEntity<String> likeArticle(@PathVariable ("idArticle") Integer idArticle, @PathVariable ("idUser") String idUser) {
        return   iArticleService.likeArticle(idArticle, idUser);
    }
    @PostMapping("/add-impotArticle/{impotId}/{ArticleId}/")
    public void addImpotArticle(@PathVariable("impotId") Integer ImpotId,@PathVariable("ArticleId") Integer ArticleId){
        iArticleService.addImpotArticle(ImpotId,ArticleId);

    }
    @PostMapping("/add-chargeArticle/{chargeid}/{ArticleId}/")
    public void addCgargeArticle(@PathVariable("chargeid") Integer chargeId,@PathVariable("ArticleId") Integer ArticleId){
        iArticleService.addChargeToArticle(chargeId,ArticleId);

    }
    @GetMapping("/tauxImpot/{articleId}/")

    public ResponseEntity<Double> calculateTauxImpot(@PathVariable Integer articleId) {
        Double taxe = Double.valueOf(0);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article not found"));
        List<AssocArticleImpot> assocList = assocArticleImpot.findByArticleId(article);

        if (assocList == null || assocList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Double sum= Double.valueOf(0);
        for (AssocArticleImpot assoc : assocList) {
            // assume that the first assoc in the list determines the tax rate
            Impot impot = assoc.getImpotId();
            Double taux = impot.getTauxImpot();
            Float prixHT = article.getPrixHT();
            // taxe = prixHT * taux;
            sum = sum + taux;
        }
        Double prixTTC = article.getPrixHT()+article.getPrixHT()*sum;
        return ResponseEntity.ok(prixTTC);
    }

    @GetMapping("/charge/{articleId}/")
    public ResponseEntity<Double> calculateCharge(@PathVariable Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article not found"));
        List<AssocArticleCharge> listCharge = assocArticleChargeRepository.findByArticleId(article);
        if (listCharge == null || listCharge.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Double sum = Double.valueOf(0f);
        for (AssocArticleCharge assoc : listCharge) {
            Charge charge = assoc.getChargeId();
            Float taux = charge.getTauxCharge();
            Float prixHT = article.getPrixHT();
            sum = sum+ taux;

        }
        Double totalCharge = article.getPrixHT()+article.getPrixHT()*sum;

        return ResponseEntity.ok(totalCharge);
    }



}


