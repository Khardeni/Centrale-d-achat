package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.LivraisonLivreur;
import tn.esprit.spring.entities.Livreur;

import java.util.List;

public interface ILLService {
    void assignLivraison(Livreur livreur, Integer livraisonId);

    void unassign(Integer llId);

    List<LivraisonLivreur> getAssignedByLivreur(Integer livreurId);
}

