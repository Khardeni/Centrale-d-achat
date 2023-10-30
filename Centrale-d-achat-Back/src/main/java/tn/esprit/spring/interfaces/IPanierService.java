package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.entities.Panier;
import tn.esprit.spring.entities.User;

import java.util.List;

public interface IPanierService {



    void createPanier(String userID);

    void deletePanier(Integer panierID);

    Panier getPanierById(Integer PID);

    Panier getPanierByUser(String userID);

    List<LignePanier> getLignePanierByUser(String idUser);

    List<Panier> getAllPanier();

    Float getTotalPrice(Panier panier);
}
