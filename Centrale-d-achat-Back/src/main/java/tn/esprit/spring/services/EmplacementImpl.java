package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Emplacement;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.interfaces.EmplacementService;
import tn.esprit.spring.repositories.*;

import java.util.*;

@Service
public class EmplacementImpl implements EmplacementService {
    public void initEmplacement() {
        Emplacement emp1 = new Emplacement();
        emp1.setEmplacementId(1);
        emp1.setGouvernorat("Bizerte");
        emp1.setNomEmplacement("Bizerta-Shop");
        emp1.setAdresseEmplacement("Bizerte, el rimel");
        emplacementRepository.save(emp1);

        Emplacement emp2 = new Emplacement();
        emp2.setEmplacementId(2);
        emp2.setGouvernorat("Tunis");
        emp2.setNomEmplacement("Centrale-Tunis");
        emp2.setAdresseEmplacement("Tunis, rue requin");
        emplacementRepository.save(emp2);


        Emplacement emp3 = new Emplacement();
        emp3.setEmplacementId(3);
        emp3.setGouvernorat("Gafsa");
        emp3.setNomEmplacement("Piece Origin");
        emp3.setAdresseEmplacement("gafsa, rue el fol");
        emplacementRepository.save(emp3);
    }
    @Autowired
    private EmplacementRepository emplacementRepository;

    @Override
    public List<Emplacement> getAllEmplacements() {
        return emplacementRepository.findAll();
    }



    @Override
    public Emplacement getEmplacementById(int id) {
        Optional<Emplacement> optionalEmplacement = emplacementRepository.findById(id);
        return optionalEmplacement.orElse(null);
    }

    @Override
    public Emplacement addEmplacement(Emplacement emplacement) {
        return emplacementRepository.save(emplacement);
    }

    @Override
    public Emplacement updateEmplacement(int id, Emplacement emplacement) {
        Optional<Emplacement> optionalEmplacement = emplacementRepository.findById(id);
        if (optionalEmplacement.isPresent()) {
            Emplacement existingEmplacement = optionalEmplacement.get();
            existingEmplacement.setNomEmplacement(emplacement.getNomEmplacement());
            existingEmplacement.setAdresseEmplacement(emplacement.getAdresseEmplacement());
            existingEmplacement.setGouvernorat(emplacement.getGouvernorat());
            return emplacementRepository.save(existingEmplacement);
        }
        return null;
    }

    @Override
    public void deleteEmplacement(int id) {
        emplacementRepository.deleteById(id);
    }


}
