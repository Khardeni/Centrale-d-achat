package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.FactureAvoir;

import java.util.List;

public interface IFactureAvoirService {

    public void addFactureAvoir (FactureAvoir factureAvoir, Integer idFacture); // ajoute une facture avoir et change l'etat de la facture original
    public List<FactureAvoir> getFacturesAvoir();
    public List<FactureAvoir> getFacturesAvoirByDate(String dateFrom, String dateTo);
    public Integer countFacturesAvoirByDate(String dateFrom, String dateTo);

    /*public void addPaiement(Paiement paiement);
    public List<Paiement> getPaiement();
    public Paiement getPaiementByFacture(Facture facture);
    public List<Paiement> getPaiementsByUser(User user);
    public List<Paiement> getPaiementsByDate(String dateFrom, String dateTo);
    public List<Paiement> getRevertedPaiements();
    */
}


