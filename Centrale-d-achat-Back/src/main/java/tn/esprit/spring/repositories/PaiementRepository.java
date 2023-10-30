package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Paiement;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Integer> {
    public Paiement findByFactureId(Facture facture);
    public List<Paiement> findByCurrencyId(Currency currency);

    public List<Paiement> findByCardNumber(Integer cardNumber);
    public List<Paiement> findByEtatPaiement(Integer etatPaiement);

    public List<Paiement> findAllByOrderByPaiementIdDesc();
}
