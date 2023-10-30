package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.LivraisonLivreur;
import tn.esprit.spring.entities.Livreur;

import java.util.List;

@Repository
public interface LivraisonLivreurRepository extends JpaRepository<LivraisonLivreur,Integer> {
    public List<LivraisonLivreur> findByLivreur(Livreur livreur);
    public LivraisonLivreur findByLivraison(Livraison livraison);
}

