package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Offre;
import tn.esprit.spring.entities.OffreDemande;

import java.util.List;

public interface IOffreDemande {

    public void addOffreDemande(OffreDemande offreDemande);
    public OffreDemande editOffreDemande(OffreDemande offreDemande);

    public String deleteOffreDemande(Integer id);

    public List<OffreDemande> getAllOffresDemande();

    public OffreDemande getOffreDemandeById(Integer id);


}
