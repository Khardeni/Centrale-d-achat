package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.*;

import java.util.List;

public interface IBackupsManager {
    public List<Facture> checkFacture();
    public List<Paiement> checkPaiement();
    public List<Livraison> checkLivraison();
    public List<Livreur> checkLivreur();
    public List<LivraisonLivreur> checkLL();
    public List<FactureAvoir> checkFactureAvoir();
    public List<Article> checkArticle();
    public List<Commande> checkOrders();
    public List<Emplacement> checkEmplacement();
    public List<Employee> checkEmployee();
    public List<Absence> checkAbsence();
    public List<User> checkUser();
}
