package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Offre;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
}