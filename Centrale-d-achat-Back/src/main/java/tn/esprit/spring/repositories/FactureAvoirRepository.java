package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.FactureAvoir;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FactureAvoirRepository extends JpaRepository<FactureAvoir,Integer> {

    public List<FactureAvoir> findByDateFacturationAvoir(LocalDate date);
}
