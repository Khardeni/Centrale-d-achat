package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.entities.Panier;
import tn.esprit.spring.entities.User;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Integer> {
  Panier findPanierByUserId(User userId);
  Panier findByUserId(User userId);

}
