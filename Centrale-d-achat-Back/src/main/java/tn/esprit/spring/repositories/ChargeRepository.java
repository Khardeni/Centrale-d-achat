package tn.esprit.spring.repositories;

import tn.esprit.spring.entities.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<Charge,Integer> {

}
