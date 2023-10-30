package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Paiement;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.interfaces.IPaiementService;
import tn.esprit.spring.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PaiementImpl implements IPaiementService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PaiementRepository paiementRepository;

    @Autowired
    FactureAvoirRepository factureAvoirRepository;

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    CommandeRepository commandeRepository;
    public void addPaiement(Paiement paiement, Integer factureId){
        Facture facture = factureRepository.findById(factureId).orElse(null);
        paiement.setFactureId(facture);
        paiementRepository.save(paiement);
    }

    @Override
    public List<Paiement> getPaiement() {
        return paiementRepository.findAllByOrderByPaiementIdDesc();
    }

    @Override
    public Paiement getPaiementByFacture(Integer factureId) {
        Facture facture = factureRepository.findById(factureId).orElse(null);
        Paiement paiement = paiementRepository.findByFactureId(facture);
        return paiement;
    }

    @Override
    public List<Paiement> getPaiementsByUser(String userID) {
        User user = userRepository.findById(userID).orElse(null);
        Paiement paiement = new Paiement();
        List<Facture> listFacture = new ArrayList<>();
        List<Paiement> listPaiement = new ArrayList<>();
        List<Commande> listCommande = commandeRepository.findByUserId(user);
        for(Commande cmd : listCommande){
            Facture facture = factureRepository.findByCommandeId(cmd);
            log.info(cmd.toString());
            log.info("test1\n");
            log.info(facture.toString());
            listFacture.add(facture);
        }
        log.info(listFacture.toString());
        for(Facture fact : listFacture){
            log.info(fact.toString());
                if (fact.getEtatFacture() == 0) {
                    paiement = paiementRepository.findByFactureId(fact);
                    listPaiement.add(paiement);
                }
        }
        return listPaiement;
    }

    @Override
    public List<Paiement> getPaiementsByCardNumber(Integer cardNumber) {
        return paiementRepository.findByCardNumber(cardNumber);
    }

    @Override
    public List<Paiement> getPaiementsByDate(String dateFrom, String dateTo) {
        List<Paiement> listPaiement = null;
        List<Facture> listFacture = null;
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            listFacture = factureRepository.findByDateFacturation(date);
            for (Facture fact : listFacture){
                if(fact.getEtatFacture()==0) {
                    Paiement paiement = paiementRepository.findByFactureId(fact);
                    listPaiement.add(paiement);
                }
            }
        }
        return listPaiement;
    }

    @Override
    public List<Paiement> getRevertedPaiements() {
        return paiementRepository.findByEtatPaiement(0);
    }

    @Override
    public void revertPaiement(Integer paiementId){
        Paiement paiement = paiementRepository.findById(paiementId).orElse(null);
        paiement.setEtatPaiement(-1);
        paiementRepository.save(paiement);
    }

}
