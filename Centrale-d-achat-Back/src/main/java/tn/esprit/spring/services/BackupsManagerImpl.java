package tn.esprit.spring.services;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.interfaces.IBackupsManager;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BackupsManagerImpl implements IBackupsManager {
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    FactureRepository factureRepository;

    @Override
    public List<Facture> checkFacture() {
        List<Facture> listFacture = factureRepository.findAll();
        System.out.println(listFacture.size());
        List<Facture> listToReturn = new ArrayList<>();
        for (Facture  f : listFacture ) {
            if(f.getCommandeId()==null){
                listToReturn.add(f);
            }
            /*if(f.getCommandeId()!=null) {
                if(f.getCommandeId().getCommandeId()==null) {
                    listToReturn.add(f);
                    //Commande cmd = commandeRepository.findById(f.getCommandeId().getCommandeId()).orElse(null);
                }
            }else{
                listToReturn.add(f);
            }*/
            Paiement paiement = paiementRepository.findByFactureId(f);
            if(paiement==null){
                listToReturn.add(f);
            }
        }


        if(listToReturn!=null){
            return listToReturn;
        }else{
            return null;
        }
    }

    @Override
    public List<Paiement> checkPaiement() {
        return null;
    }

    @Override
    public List<Livraison> checkLivraison() {
        return null;
    }

    @Override
    public List<Livreur> checkLivreur() {
        return null;
    }

    @Override
    public List<LivraisonLivreur> checkLL() {
        return null;
    }

    @Override
    public List<FactureAvoir> checkFactureAvoir() {
        return null;
    }

    @Override
    public List<Article> checkArticle() {
        return null;
    }

    @Override
    public List<Commande> checkOrders() {
        return null;
    }

    @Override
    public List<Emplacement> checkEmplacement() {
        return null;
    }

    @Override
    public List<Employee> checkEmployee() {
        return null;
    }

    @Override
    public List<Absence> checkAbsence() {
        return null;
    }

    @Override
    public List<User> checkUser() {
        return null;
    }
}
