package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Marque;

public interface MarqueRepository extends JpaRepository<Marque,Integer> {
}
