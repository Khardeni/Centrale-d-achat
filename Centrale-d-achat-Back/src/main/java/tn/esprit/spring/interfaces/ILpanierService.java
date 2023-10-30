package tn.esprit.spring.interfaces;

import tn.esprit.spring.DTO.CommandeHistorique;
import tn.esprit.spring.DTO.LignePanierDto;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.LignePanier;

import java.util.List;

public interface ILpanierService {

    List<CommandeHistorique> getLPbyPanier(Integer panierId);
    List<CommandeHistorique> houssemYlawej3laLista(Integer commandeId);
    LignePanier AddArticleToLigne(LignePanierDto lignePanierDto);

    List<LignePanier> getAllLignePaniers();

    void deleteLignePanierById(Integer code);

    void deleteByPanier(Integer panierId);

    LignePanier updateQuantityFromLignePanier(Integer lignePanierId, int quantity);

    void updateStatus(Integer idLignePanier);

    List<LignePanier> ValidateOrderByUser(String userID);

    List<CommandeHistorique> getCheckoutDetails(Integer panierId);
}
