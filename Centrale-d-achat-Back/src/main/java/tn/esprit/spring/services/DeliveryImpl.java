package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DTO.CheckStockDTO;
import tn.esprit.spring.DTO.LivraisonDetailleDTO;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.IDeliveryService;
import tn.esprit.spring.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DeliveryImpl implements IDeliveryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private EmplacementRepository emplacementRepository;
    @Autowired
    private ArticleEmplacementRepository articleEmplacementRepository;
    @Autowired
    private LivreurRepository livreurRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    RetourRepository retourRepository;
//longitude latitude//gestion car park//l
    @Override
    public void addDelivery(CheckStockDTO csd) {
        Emplacement emplacement = new Emplacement();
        String adliv = csd.getLivraison().getAdresseLivraison().toLowerCase();
        if(  (adliv.contains("tunis")) || (adliv.contains("ben arous"))  || (adliv.contains("manouba")) || (adliv.contains("ariana"))  || (adliv.toLowerCase().contains("zaghouan"))  || adliv.toLowerCase().contains("nabeul")  ){
            emplacement = emplacementRepository.findByGouvernorat("Tunis");
        }else if(   (adliv.toLowerCase().contains("bizerte"))   ||  (adliv.toLowerCase().contains("beja"))  || (adliv.toLowerCase().contains("jendouba"))  || (adliv.toLowerCase().contains("kef")) ){
            emplacement = emplacementRepository.findByGouvernorat("Bizerte");
        }else if(   (adliv.toLowerCase().contains("kasserine"))   ||  (adliv.toLowerCase().contains("gafsa"))  || (adliv.toLowerCase().contains("tozeur"))  || (adliv.toLowerCase().contains("sidi bouzid")) || (adliv.toLowerCase().contains("kairouan")) ){
            emplacement = emplacementRepository.findByGouvernorat("Gafsa");
        }
        List<ArticleEmplacement> aeList = articleEmplacementRepository.findByEmplacement(emplacement);
        Integer dispo = 1;
        List<LignePanier> lignePanierList=csd.getLpList();
        for(LignePanier lp : lignePanierList){
            ArticleEmplacement ae = articleEmplacementRepository.findByArticle(lp.getArticle());
            if(ae.getStockE()>=lp.getQuantite()){
                ae.setStockE(ae.getStockE()-lp.getQuantite());
                articleEmplacementRepository.save(ae);
            }else if(ae.getStockE()<=lp.getQuantite()){
                log.info("stock insuffisant") ;
            }
        }

        csd.getLivraison().setEmplacementId(emplacement);
        csd.getLivraison().setEtatLivraison(0);
        csd.getLivraison().setDateEnvoi(null);
        csd.getLivraison().setDateLivraisonPrevu(LocalDate.now().plusDays(5));
        csd.getLivraison().setDateLivraison(null);
        Facture facture = factureRepository.findById(csd.getFactureId()).orElse(null);
        Commande commande = commandeRepository.findById(csd.getCommandeId()).orElse(null);
        csd.getLivraison().setCommandeId(commande);
        csd.getLivraison().setFactureId(facture);
        deliveryRepository.save(csd.getLivraison());
    }

    @Override
    public void EditDelivery(Livraison livraison, Integer livraisonId) {
        livraison.setLivraisonId(livraisonId);
        deliveryRepository.save(livraison);
    }

    @Override
    public List<Livraison> getDeliveries(){
        List<Livraison> listDeliveries = deliveryRepository.findAllByOrderByLivraisonIdDesc();
        return listDeliveries;
    }

    @Override
    public List<Livraison> getDeliveriesByDate(String dateFrom, String dateTo){
        List<Livraison> listDeliveries = null;
        List<Livraison> deliveryTmp = null;
        LocalDate startDate = LocalDate.parse(dateFrom);
        LocalDate endDate = LocalDate.parse(dateTo);
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1))
        {
            deliveryTmp = deliveryRepository.findByDateLivraison(date);
            for (Livraison delivery : deliveryTmp){
                listDeliveries.add(delivery);
            }
        }
        return listDeliveries;
    }

    @Override
    public Livraison getDeliveryById(Integer livraisonId) {
        return deliveryRepository.findById(livraisonId).orElse(null);
    }

    @Override
    public void setDeliveryToShipping(Integer livraisonId) {
        Livraison livraison = deliveryRepository.findByLivraisonId(livraisonId);
        livraison.setDateEnvoi(LocalDate.now());
        deliveryRepository.save(livraison);
    }

    @Override
    public void setDeliveryToShipped(Integer livraisonId) {
        Livraison livraison = deliveryRepository.findByLivraisonId(livraisonId);
        livraison.setDateLivraison(LocalDate.now());
        deliveryRepository.save(livraison);
    }

    @Override
    public Facture getFactureIdByDeliveryId(Integer livraisonId){
        Livraison livraison = deliveryRepository.findById(livraisonId).orElse(null);
        return livraison.getFactureId();
    }

    @Override
    public LivraisonDetailleDTO getDeliveryDetails(Integer livraisonId) {
        Livraison delivery = deliveryRepository.findById(livraisonId).orElse(null);
        User user = userRepository.findByUserName(delivery.getCommandeId().getUserId().getUserName());
        LivraisonDetailleDTO ldd = new LivraisonDetailleDTO();
        ldd.setAdresseLivraison(delivery.getAdresseLivraison());
        ldd.setEmplacementOrigine(delivery.getEmplacementId().getAdresseEmplacement());
        ldd.setCommandeId(delivery.getCommandeId().getCommandeId());
        ldd.setTypeLivraison(delivery.getTypeLivraison());
        ldd.setDateEnvoi(delivery.getDateEnvoi());
        ldd.setDateLivraison(delivery.getDateLivraison());
        ldd.setNumTelReception(user.getUserPhone());
        ldd.setFactureId(delivery.getFactureId().getFactureId());
        ldd.setDateLivraisonPrevu(delivery.getDateLivraisonPrevu());
        ldd.setEtatLivraison(delivery.getEtatLivraison());
        ldd.setLivraisonId(delivery.getLivraisonId());
        return ldd;
    }

    @Override
    public List<LivraisonDetailleDTO> getDeliveryHistory(String username) {
        List<LivraisonDetailleDTO> listldd = new ArrayList<>();
        List<Commande> listc = new ArrayList<>();
        List<Livraison> listd = new ArrayList<>();
        User user = userRepository.findByUserName(username);
        listc = commandeRepository.findByUserId(user);
        for (Commande c : listc) {
            Facture facture = factureRepository.findByCommandeId(c);
            Livraison delivery = deliveryRepository.findByFactureId(facture);
            LivraisonDetailleDTO ldd = new LivraisonDetailleDTO();
            ldd.setAdresseLivraison(delivery.getAdresseLivraison());
            ldd.setEmplacementOrigine(delivery.getEmplacementId().getAdresseEmplacement());
            ldd.setCommandeId(delivery.getCommandeId().getCommandeId());
            ldd.setTypeLivraison(delivery.getTypeLivraison());
            ldd.setDateEnvoi(delivery.getDateEnvoi());
            ldd.setDateLivraison(delivery.getDateLivraison());
            ldd.setNumTelReception(user.getUserPhone());
            ldd.setFactureId(delivery.getFactureId().getFactureId());
            ldd.setDateLivraisonPrevu(delivery.getDateLivraisonPrevu());
            ldd.setEtatLivraison(delivery.getEtatLivraison());
            ldd.setLivraisonId(delivery.getLivraisonId());
            listldd.add(ldd);
        }
        return listldd;
    }

    @Override
    public Integer getPendingDeliveries() {
        return deliveryRepository.findByDateLivraisonNull();
    }
    @Override
    public Integer getLateDeliveries() {
        return deliveryRepository.findLate();
    }

}
