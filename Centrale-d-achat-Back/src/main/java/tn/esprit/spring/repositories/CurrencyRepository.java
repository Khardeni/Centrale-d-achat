package tn.esprit.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Currency;

@Repository

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
  //  Currency findByNameIgnoreCase(String Name);
}
