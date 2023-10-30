package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.Livreur;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur,Integer> {

    public List<Livreur> findByNomLivreurLike(String nomLivreur);
    public List<Livreur> findByDateAjout(LocalDate dateEnlevement);
    public List<Livreur> findBySocieteLivraison(String nomSociete);

    public Livreur findByNomLivreurAndSocieteLivraisonAndNumLivreur(String nomLivreur,String nomSociete,String numLivreur);
}
