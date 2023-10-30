package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DTO.CommandeHistorique;
import tn.esprit.spring.DTO.FactureDetailDTO;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IFactureService;
import tn.esprit.spring.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FactureImpl implements IFactureService {
    @Autowired
    private DeliveryRepository deliveryRepository;
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
    @Override
    public void addFacture(Facture facture, Integer commandeId) {
        Commande commande = commandeRepository.findById(commandeId).orElse(null);
        facture.setCommandeId(commande);
        facture.setDateFacturation(LocalDate.now());
        factureRepository.save(facture);
    }

    @Override
    public List<Facture> getFacturesByDate(String dateFrom, String dateTo){
        List<Facture> listFactures = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            log.info(String.valueOf(date));
            List<Facture> factureTmp = factureRepository.findAllByDateFacturation(date);
            log.info(String.valueOf(factureTmp.isEmpty()));
            log.info(factureTmp.toString());
            if(factureTmp.isEmpty()==false) {
                for (Facture facture : factureTmp) {
                    listFactures.add(facture);
                }
            }
            factureTmp = null;
        }
        return listFactures;
    }

    @Override
    public Facture getFactureById(Integer factureId){
        Facture facture = factureRepository.findById(factureId).orElse(null);
        return facture;
    }

    @Override
    public FactureDetailDTO getFactureDetail(Integer factureId){
        FactureDetailDTO df = new FactureDetailDTO();
        Facture facture = factureRepository.findById(factureId).orElse(null);
        Livraison livraison = deliveryRepository.findByFactureId(facture);
        Commande commande = commandeRepository.findById(facture.getCommandeId().getCommandeId()).orElse(null);
        User user = userRepository.findByUserName(commande.getUserId().getUserName());
        df.setAdresseLivraison(livraison.getAdresseLivraison());
        df.setNomPrenomClient(user.getUserFirstName()+' '+user.getUserLastName());
        df.setDateCommmande(commande.getDateCommande());
        df.setEtatFacture(facture.getEtatFacture());
        df.setTelephoneReceptionLivraison(user.getUserPhone());
        return df;
    }
    @Override
    public Facture getFactureByCommande(Integer commandeId){
        Commande commande = commandeRepository.findById(commandeId).orElse(null);
        Facture facture = factureRepository.findByCommandeId(commande);
        return facture;
    }

    @Override
    public List<Facture> getFacturesByUser(String userId){
        //Facture facture = new Facture();
        log.info(String.valueOf(userId));
        log.info("fin des tests");
        List<Facture> listFactures = new ArrayList<>();
        User user = userRepository.findById(String.valueOf(userId)).orElse(null);
        List<Commande> listCmd = commandeRepository.findByUserId(user);
        log.info(listCmd.toString());
        if(listCmd.isEmpty()==false){
            for (Commande cmd : listCmd){
                log.info(cmd.toString());
                Facture facture = factureRepository.findByCommandeId(cmd);
                log.info(facture.toString());
                log.info("fin des tests");
                listFactures.add(facture);
            }
        }
        return listFactures;
    }

    @Override
    public Facture getFactureByFactureAvoir(FactureAvoir factureAvoir){
        Facture facture = factureRepository.findById(factureAvoir.getFactureId().getFactureId()).orElse(null);
        return facture;
    }

    @Override
    public List<Facture> getFactures(){
        List<Facture> listFactures = factureRepository.findAllByOrderByFactureIdDesc();
        return listFactures;
    }

    @Override
    public List<Double> getAnnualRevenue(Integer year) {
        List<Double> revenueMensuel = new ArrayList<>();
        Double rm = Double.valueOf(0);
        Integer daysCount=0;
        for(int i=1;i<=11;i++){
            if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12){
                daysCount=31;
            }else if(i==4 || i==6 || i==9 || i==11 ){
                daysCount=30;
            }else{
                daysCount=28;
            }
            LocalDate startDate = LocalDate.of(year,i,1);
            LocalDate endDate = LocalDate.of(year,i,daysCount);
            List<Facture> listFact = factureRepository.findByDateFacturationBetween(startDate,endDate);
            for (Facture fact : listFact) {
                rm = rm + fact.getPrixTTC();
            }
            revenueMensuel.add(rm);
            rm=0.0;
        }
        return revenueMensuel;
    }

   /* @Override
    public Float getGainsByDate(String dateFrom, String dateTo){
        Float gains= Float.valueOf(0);
        List<Facture> listFactures = null;
        List<Facture> factureTmp = null;
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            factureTmp = factureRepository.findByDateFacturation(date);
            for (Facture facture : factureTmp){
                listFactures.add(facture);
            }
        }
        for(Facture f:listFactures){
            gains=f.
        }
        return listFactures;
    }*/



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
