package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Commande;

import java.time.LocalDate;
import java.util.List;

public interface ICommandeService {
    Commande AjouterCommande(Commande commande);

    Commande GetCommandeById(Integer cmdId);

    List<Commande> GetListCommandes();

    List<Commande> GetCommandeByUser(String UID);

    List<Commande> GetCommandeByDate(LocalDate date);
}
