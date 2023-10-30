package tn.esprit.spring.interfaces;


import tn.esprit.spring.DTO.FactureDetailDTO;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.FactureAvoir;

import java.util.List;

public interface IFactureService {
    public void addFacture (Facture facture, Integer commandeId);
    public List<Facture> getFacturesByDate(String dateFrom, String dateTo);

    FactureDetailDTO getFactureDetail(Integer factureId);

    public Facture getFactureByCommande(Integer commandeId);
    public List<Facture> getFacturesByUser(String userId);
    public Facture getFactureByFactureAvoir(FactureAvoir factureAvoir);
    public List<Facture> getFactures ();
    public List<Double> getAnnualRevenue(Integer year);
    public Facture getFactureById(Integer factureId);
    //public Float getGainsByDate(String dateFrom, String dateTo);
    //public Float getGainsByFacture(Facture facture);

}


