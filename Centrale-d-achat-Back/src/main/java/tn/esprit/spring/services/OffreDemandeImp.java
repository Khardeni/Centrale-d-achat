package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.controllers.OffreDemandeController;
import tn.esprit.spring.entities.OffreDemande;
import tn.esprit.spring.interfaces.IOffreDemande;
import tn.esprit.spring.repositories.OffreDemandeRepository;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class OffreDemandeImp implements IOffreDemande {

    OffreDemandeRepository offreDemandeRepository;


    @Override
    public void addOffreDemande(OffreDemande offreDemande) {


        offreDemande.setStatusOD(offreDemande.getStatusOD());
        offreDemande.setDateOD(offreDemande.getDateOD());
        offreDemandeRepository.save(offreDemande);
    }

    @Override
    public OffreDemande editOffreDemande(OffreDemande offreDemande) {

        return offreDemandeRepository.save(offreDemande);
    }

    @Override
    public String deleteOffreDemande(Integer id) {
        offreDemandeRepository.deleteById(id);
        return "Entry Deleted";
    }

    @Override
    public List<OffreDemande> getAllOffresDemande() {
        return offreDemandeRepository.findAll();
    }

    @Override
    public OffreDemande getOffreDemandeById(Integer id) {
        return offreDemandeRepository.findById(id).get();
    }
}
