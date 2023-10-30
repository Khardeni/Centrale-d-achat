package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Retour;

@Repository
public interface RetourRepository extends JpaRepository<Retour,Integer> {


}
