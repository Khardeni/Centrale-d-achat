package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IRetourService;
import tn.esprit.spring.repositories.*;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RetourImpl implements IRetourService {
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private FactureAvoirRepository factureAvoirRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    RetourRepository retourRepository;

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    PaiementImpl paiementService;

    @Override
    public void CancelDelivery(Integer livraisonId) {
        Retour retour = new Retour();
        Livraison livraison = deliveryRepository.findById(livraisonId).orElse(null);
        Commande commande = commandeRepository.findById(livraison.getCommandeId().getCommandeId()).orElse(null);
        Facture facture = factureRepository.findByCommandeId(commande);
        Paiement paiement = paiementRepository.findByFactureId(facture);
        FactureAvoir factureAvoir = new FactureAvoir();
        livraison.setEtatLivraison(-1);
        deliveryRepository.save(livraison);
        retour.setLivraisonId(livraison);
        retour.setDateAnnulation(LocalDate.now());
        retour.setDateRetour(null);
        retourRepository.save(retour);
        factureAvoir.setPrixTTC(facture.getPrixTTC());
        factureAvoir.setFactureId(facture);
        factureAvoir.setDateFacturationAvoir(LocalDate.now());
        factureAvoir.setEtatFacture(0);
        factureAvoirRepository.save(factureAvoir);
        facture.setEtatFacture(-1);
        factureRepository.save(facture);
        paiementService.revertPaiement(paiement.getPaiementId());
    }

    @Override
    public List<Retour> getCanceledDeliveries(){
        List<Retour> listCanceledDeliveries = retourRepository.findAll();
        return listCanceledDeliveries;
    }

}
