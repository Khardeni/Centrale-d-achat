package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DTO.CommandeHistorique;
import tn.esprit.spring.DTO.LignePanierDto;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.ILpanierService;
import tn.esprit.spring.repositories.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class LpanierImpl implements ILpanierService {

    @Autowired
    LpanierRepository lpanierRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    PanierImpl panierImpl;
    @Autowired
    CommandeImpl commandeImpl;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<CommandeHistorique> getLPbyPanier(Integer panierId) {
        List<CommandeHistorique> lch = new ArrayList<>();
        Panier panier = panierRepository.findById(panierId).orElse(null);
        List<LignePanier> llp = lpanierRepository.findByPanierAndStatusLessThan(panier,1);
        for(LignePanier lp : llp){
            CommandeHistorique ch = new CommandeHistorique();
            if(lp.getArticle().getCategorieArticle()!=null) {
                ch.setCategorie(lp.getArticle().getCategorieArticle().getNomCategorie());
            }else{
                ch.setCategorie("UNAVAILABLE");
            }
            if(lp.getArticle().getMarqueId()!=null) {
                ch.setMarque(lp.getArticle().getMarqueId().getNomMarque());
            }else{
                ch.setMarque("UNAVAILABLE");
            }
            ch.setStatus(lp.getStatus());
            ch.setLignePanierId(lp.getLignePanierId());
            ch.setLikeCount(lp.getArticle().getLikeCount());
            ch.setPrixArticle(lp.getArticle().getPrixHT()*1.19);
            ch.setQuantite(lp.getQuantite());
            ch.setDateAdded(lp.getDateAdded());
            ch.setNomArticle(lp.getArticle().getNomArticle());
            ch.setImage(lp.getArticle().getImagee());
            lch.add(ch);
        }
        return lch;
    }

    @Override
    public List<CommandeHistorique> getCheckoutDetails(Integer panierId) {
        List<CommandeHistorique> lch = new ArrayList<>();
        Panier panier = panierRepository.findById(panierId).orElse(null);
        List<LignePanier> llp = lpanierRepository.findByPanierAndStatus(panier,-1);
        for(LignePanier lp : llp){
            CommandeHistorique ch = new CommandeHistorique();
            if(lp.getArticle().getCategorieArticle()!=null) {
                ch.setCategorie(lp.getArticle().getCategorieArticle().getNomCategorie());
            }else{
                ch.setCategorie("UNAVAILABLE");
            }
            if(lp.getArticle().getMarqueId()!=null) {
                ch.setMarque(lp.getArticle().getMarqueId().getNomMarque());
            }else{
                ch.setMarque("UNAVAILABLE");
            }
            ch.setStatus(lp.getStatus());
            ch.setLignePanierId(lp.getLignePanierId());
            ch.setLikeCount(lp.getArticle().getLikeCount());
            ch.setPrixArticle(lp.getArticle().getPrixHT()*1.19);
            ch.setQuantite(lp.getQuantite());
            ch.setDateAdded(lp.getDateAdded());
            ch.setNomArticle(lp.getArticle().getNomArticle());
            ch.setImage(lp.getArticle().getImagee());
            lch.add(ch);
        }
        return lch;
    }

    /*** Houssem wrote this method ***/
    @Override
    public List<CommandeHistorique> houssemYlawej3laLista(Integer commandeId){
        List<CommandeHistorique> lch = new ArrayList<>();
        List<LignePanier> llp = lpanierRepository.findByStatus(commandeId);
        for(LignePanier lp : llp){
            CommandeHistorique ch = new CommandeHistorique();
            if(lp.getArticle().getCategorieArticle()!=null) {
                ch.setCategorie(lp.getArticle().getCategorieArticle().getNomCategorie());
            }else{
                ch.setCategorie("UNAVAILABLE");
            }
            if(lp.getArticle().getMarqueId()!=null) {
                ch.setMarque(lp.getArticle().getMarqueId().getNomMarque());
            }else{
                ch.setMarque("UNAVAILABLE");
            }
            ch.setLignePanierId(lp.getLignePanierId());
            ch.setStatus(lp.getStatus());
            ch.setImage(lp.getArticle().getImagee());
            ch.setLikeCount(lp.getArticle().getLikeCount());
            ch.setPrixArticle(lp.getArticle().getPrixHT()*1.19);
            ch.setQuantite(lp.getQuantite());
            ch.setDateAdded(lp.getDateAdded());
            ch.setNomArticle(lp.getArticle().getNomArticle());
            ch.setImage(lp.getArticle().getImagee());
            lch.add(ch);
        }
        return lch;
    }
    @Override
    public LignePanier AddArticleToLigne(LignePanierDto lignePanierDto) {
        int v=0;
        Article article= articleRepository.findById(lignePanierDto.getIdArticle()).orElse(null);
        Panier panier= panierRepository.findById(lignePanierDto.getIdPanier()).orElse(null);
        List<LignePanier> lignePanierList= lpanierRepository.findAllByArticleAndPanier(article,panier);
        LignePanier lignePanier = null;
        for(LignePanier lp : lignePanierList){
            if(lp.getStatus()==0){
                lignePanier=lp;
            }
        }
            if (lignePanierDto.getQuantite() != 0) {
                if (lignePanier!=null && lignePanier.getStatus() == 0) {
                    v = 1;
                    lignePanier.setQuantite(lignePanier.getQuantite() + lignePanierDto.getQuantite());
                    lignePanier.setDateAdded(new Date());
                    lpanierRepository.save(lignePanier);
                    panier.setCoutTotal(panierImpl.getTotalPrice(panier));
                    panierRepository.save(panier);
                } else if (lignePanier == null || lignePanier.getStatus() != 0) {
                    v = 2;
                    lignePanier = new LignePanier();
                    lignePanier.setPanier(panier);
                    lignePanier.setArticle(article);
                    lignePanier.setQuantite(lignePanierDto.getQuantite());
                    lignePanier.setDateAdded(new Date());
                    lignePanier.setStatus(0);
                    panier.setNbrArticle(panier.getNbrArticle() + 1);
                    lpanierRepository.save(lignePanier);
                    panier.setCoutTotal(panierImpl.getTotalPrice(panier));
                    panierRepository.save(panier);
                }

                if (v != 0) {
                    //lpanierRepository.save(lignePanier);
                }
            } else {
                lpanierRepository.delete(lignePanier);
                panier.setCoutTotal(panierImpl.getTotalPrice(panier));
                panierRepository.save(panier);
            }
        return lignePanier;
    }




    @Override
    public List<LignePanier> getAllLignePaniers() {
        List<LignePanier> panierList = lpanierRepository.findAll();
        return panierList;
    }
    @Override
    public void deleteLignePanierById(Integer code) {
        lpanierRepository.deleteById(code);
    }
    @Override
    public void deleteByPanier(Integer panierId) {
        Panier panier= panierRepository.findById(panierId).orElse(null);
        lpanierRepository.deleteAllByPanier(panier);

    }

      /**  public List<LignePanier> getLignePaniersByArticle(Article article) {

            return lpanierRepository.getByArticle(article);

        }
**/
     /** public LignePanier addQuantityToLignePanier(Integer lignePanierId, int quantity) {
          LignePanier lignePanier=lpanierRepository.findById(lignePanierId).orElse(null);
          lignePanier.setQuantite(lignePanier.getQuantite() + quantity);
          return lpanierRepository.save(lignePanier);
      }*/
     @Override
    public LignePanier updateQuantityFromLignePanier(Integer lignePanierId, int quantity) {
        int newQuantity=0;
        LignePanier lignePanier=lpanierRepository.findById(lignePanierId).orElse(null);
        if ((quantity==-1) || (quantity==1)) {

            newQuantity = lignePanier.getQuantite() + quantity;
        }
        else { newQuantity=quantity;}

        if (newQuantity <= 0) {
            lpanierRepository.delete(lignePanier);
            return null;
        } else {
            lignePanier.setQuantite(newQuantity);
            return lpanierRepository.save(lignePanier);
        }
    }

    @Override
    public void updateStatus(Integer idLignePanier) {
        LignePanier lignePanier = lpanierRepository.findById(idLignePanier).orElse(null);
        if (lignePanier != null) {
            if(lignePanier.getStatus()==0) {
                lignePanier.setStatus(-1);
            }else if(lignePanier.getStatus()==-1){
                lignePanier.setStatus(0);
            }
            lpanierRepository.save(lignePanier);
        }
    }

    @Override
    public List<LignePanier> ValidateOrderByUser(String userID) {
         Commande cmd= new Commande();
         User user= userRepository.findByUserName(userID);
         cmd.setUserId(user);
         cmd.setDateCommande(LocalDate.now());
         commandeImpl.AjouterCommande(cmd);
         log.info(cmd.getCommandeId().toString());
         Panier panier = panierRepository.findByUserId(user);
         List<LignePanier> lignePanierList= lpanierRepository.findByPanierAndStatus(panier,-1);
         for (LignePanier lignePanier : lignePanierList){
             lignePanier.setStatus(cmd.getCommandeId());
             lpanierRepository.save(lignePanier);
             panier.setNbrArticle(panier.getNbrArticle()-1);
             panier.setCoutTotal((panier.getCoutTotal())-(lignePanier.getArticle().getPrixHT()*lignePanier.getQuantite()));
             panierRepository.save(panier);
         }
         return lignePanierList;


    }




}
