package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.Livreur;

import java.util.List;

public interface ILivreurService {
    public void addLivreur (Livreur livreur);
    public void editLivreur (Livreur livreur, Integer livreurId);
    public List<Livreur> trackDeliveriesByNomLivreur(String nomLivreur);
    public List<Livreur> trackDeliveriesBySociete(String nomSociete);
    public List<Livreur> trackDeliveriesByDate(String dateEnlevement);
    public Livreur trackDeliveryByLivraison(Integer livraisonId);
    public void deleteLivreur(Integer liveurId);
}

