package tn.esprit.spring.repositories;

import tn.esprit.spring.entities.Impot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpotRepository extends JpaRepository<Impot,Integer> {

}
