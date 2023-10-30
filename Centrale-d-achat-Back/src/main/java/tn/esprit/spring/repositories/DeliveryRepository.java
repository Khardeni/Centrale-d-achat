package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Livraison;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Livraison,Integer> {

    public List<Livraison> findByDateLivraison(LocalDate date);
    public Livraison findByFactureId(Facture facture);

    public Livraison findByLivraisonId(Integer livraisonId);
    @Query("SELECT COUNT(u) FROM Livraison u WHERE u.dateLivraison=null")
    Integer findByDateLivraisonNull();
    @Query("SELECT COUNT(u) FROM Livraison u WHERE u.dateLivraisonPrevu<=CURRENT_TIMESTAMP()")
    Integer findLate();

    public List<Livraison> findAllByOrderByLivraisonIdDesc();

}
