package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.OffreDemande;

public interface OffreDemandeRepository extends JpaRepository<OffreDemande, Integer> {
}