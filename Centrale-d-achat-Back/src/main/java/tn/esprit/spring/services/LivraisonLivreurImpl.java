package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.LivraisonLivreur;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.interfaces.ILLService;
import tn.esprit.spring.repositories.DeliveryRepository;
import tn.esprit.spring.repositories.LivraisonLivreurRepository;
import tn.esprit.spring.repositories.LivreurRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class LivraisonLivreurImpl implements ILLService {
    @Autowired
    private LivraisonLivreurRepository livraisonLivreurRepository;

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    LivreurRepository livreurRepository;

    @Override
    public void assignLivraison(Livreur livreur, Integer livraisonId){
        Livraison livraison = deliveryRepository.findById(livraisonId).orElse(null);
        //Livreur livreur = livreurRepository.findById(livreurId).orElse(null);
        LivraisonLivreur ll = new LivraisonLivreur();
        livreur.setDateAjout(LocalDate.now());
        Livreur lv = livreurRepository.findByNomLivreurAndSocieteLivraisonAndNumLivreur(livreur.getNomLivreur(),livreur.getSocieteLivraison(),livreur.getNumLivreur());
        if (lv==null) {
            lv = livreurRepository.save(livreur);
        }
        ll.setLivraison(livraison);
        ll.setLivreur(lv);
        livraisonLivreurRepository.save(ll);
    }

    @Override
    public void unassign(Integer llId){
        livraisonLivreurRepository.deleteById(llId);
    }

    @Override
    public List<LivraisonLivreur> getAssignedByLivreur(Integer livreurId){
        Livreur livreur = livreurRepository.findById(livreurId).orElse(null);
        return livraisonLivreurRepository.findByLivreur(livreur);
    }

}
