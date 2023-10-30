package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.Paiement;

import java.util.List;

public interface IPaiementService {

    public void addPaiement(Paiement paiement, Integer factureId);
    public List<Paiement> getPaiement();
    public Paiement getPaiementByFacture(Integer factureId);
    public List<Paiement> getPaiementsByUser(String userID);
    public List<Paiement> getPaiementsByCardNumber(Integer cardNumber);
    public List<Paiement> getPaiementsByDate(String dateFrom, String dateTo);
    public List<Paiement> getRevertedPaiements();
    public void revertPaiement(Integer paiementId);
}


