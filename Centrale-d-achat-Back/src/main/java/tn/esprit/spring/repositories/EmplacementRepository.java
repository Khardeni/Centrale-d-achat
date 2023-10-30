package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Department;
import tn.esprit.spring.entities.Emplacement;

// Annotation
@Repository

// Interface
public interface EmplacementRepository
        extends JpaRepository<Emplacement, Integer> {
    public Emplacement findByGouvernorat(String gouvernorat);
}
