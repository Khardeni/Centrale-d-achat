package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.LivraisonLivreur;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.interfaces.ILivreurService;
import tn.esprit.spring.repositories.*;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class LivreurImpl implements ILivreurService {
    @Autowired
    private LivraisonLivreurRepository livraisonLivreurRepository;

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    LivreurRepository livreurRepository;

    @Override
    public void addLivreur (Livreur livreur){
        livreurRepository.save(livreur);
    }

    @Override
    public void editLivreur (Livreur livreur, Integer livreurId){
        livreur.setLivreurId(livreurId);
        Livreur ol = livreurRepository.findById(livreurId).orElse(null);
        livreurRepository.save((livreur));
    }

    @Override
    public List<Livreur> trackDeliveriesByNomLivreur(String nomLivreur){
        List<Livreur> listLivreur = livreurRepository.findByNomLivreurLike("%"+nomLivreur+"%");
        return listLivreur;
    }

    @Override
    public List<Livreur> trackDeliveriesBySociete(String nomSociete){
        List<Livreur> listLivreur = livreurRepository.findBySocieteLivraison(nomSociete);
        return listLivreur;
    }

    @Override
    public List<Livreur> trackDeliveriesByDate(String dateEnlevement){
        LocalDate de = LocalDate.parse(dateEnlevement);
        List<Livreur> listLivreur = livreurRepository.findByDateAjout(de);
        return listLivreur;
    }

    @Override
    public Livreur trackDeliveryByLivraison(Integer livraisonId){
        Livraison livraison = deliveryRepository.findById(livraisonId).orElse(null);
        LivraisonLivreur ll = livraisonLivreurRepository.findByLivraison(livraison);
        Livreur livreur = livreurRepository.findById(ll.getLivreur().getLivreurId()).orElse(null);
        return livreur;
    }

    @Override
    public void deleteLivreur(Integer liveurId){
        livreurRepository.deleteById(liveurId);
    }
}
