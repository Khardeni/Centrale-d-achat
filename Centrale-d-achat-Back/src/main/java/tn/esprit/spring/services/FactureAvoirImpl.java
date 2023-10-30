package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.FactureAvoir;
import tn.esprit.spring.interfaces.IFactureAvoirService;
import tn.esprit.spring.repositories.CommandeRepository;
import tn.esprit.spring.repositories.FactureAvoirRepository;
import tn.esprit.spring.repositories.FactureRepository;
import tn.esprit.spring.repositories.PaiementRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class FactureAvoirImpl implements IFactureAvoirService {

    @Autowired
    PaiementRepository paiementRepository;

    @Autowired
    FactureAvoirRepository factureAvoirRepository;

    @Autowired
    FactureRepository factureRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Override
    public void addFactureAvoir(FactureAvoir factureAvoir,Integer idFacture) {
        FactureAvoir fa = factureAvoir;
        Facture facture = factureRepository.findById(idFacture).orElse(null);
        fa.setFactureId(facture);
        facture.setEtatFacture(-1);
        factureRepository.save(facture);
        factureAvoirRepository.save(fa);
    }

    @Override
    public List<FactureAvoir> getFacturesAvoir(){
        List<FactureAvoir> listFacturesAvoir = factureAvoirRepository.findAll();
        return listFacturesAvoir;
    }

    @Override
    public Integer countFacturesAvoirByDate(String dateFrom, String dateTo){
        List<FactureAvoir> listFacturesAvoir = null;
        List<FactureAvoir> factureAvTmp = null;
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            factureAvTmp = factureAvoirRepository.findByDateFacturationAvoir(date);
            for (FactureAvoir factureAvoir : factureAvTmp){
                listFacturesAvoir.add(factureAvoir);
            }
        }
        return listFacturesAvoir.size();
    }

    @Override
    public List<FactureAvoir> getFacturesAvoirByDate(String dateFrom, String dateTo){
        List<FactureAvoir> listFacturesAvoir = null;
        List<FactureAvoir> factureAvTmp = null;
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            factureAvTmp = factureAvoirRepository.findByDateFacturationAvoir(date);
            for (FactureAvoir factureAvoir : factureAvTmp){
                listFacturesAvoir.add(factureAvoir);
            }
        }
        return listFacturesAvoir;
    }


   //@Scheduled(fixedRate = 300000) ou bien
  //@Scheduled(cron = "*/30 * * * * *")
/*
    @Override
    public void getNbrStudentByFormation() {

        List<Commande> listCommande =  factureRepository.findAll();

        listCommande.forEach(formation -> {
            log.info("La formation  "+ formation.getTitle()+" contient : "+ formation.getListFactures().size()+ "apprenants");
        });

    }

    @Override
    public Integer getProfessorRemunerationByDate(Integer idProfessor, LocalDate dateDebut, LocalDate dateFin) {
    Integer somme = 0;
        Paiement paiement = deliveryRepository.findById(idProfessor).orElse(null);
        for (Commande commande : paiement.getListCommandes()){
            if(commande.getDateDebut().isAfter(dateDebut) && commande.getDateFin().isBefore(dateFin)){
                somme +=  commande.getNbHeure()* commande.getPaiement().getTarifHoraire();
            }
        }
        return somme;
    }

    @Override
    public Integer getRevenuByFormation(Integer idFormation) {
        Commande commande = factureRepository.findById(idFormation).orElse(null);
      return  commande.getListFactures().size() * commande.getFrais();
    }
    
    public List<Paiement> getProfessor() {
        List<Paiement> listPaiement =  deliveryRepository.findAll();
        return listPaiement;
    }

    public List<Commande> getFormations() {
        List<Commande> listCommande =  factureRepository.findAll();
        return listCommande;
    }

    public List<Facture> getStudents() {
        List<Facture> listFacture =  paiementRepository.findAll();
        return listFacture;
    }*/
}
