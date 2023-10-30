package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.CurrencyHistory;

import java.util.List;

@Repository

public interface CurrencyHistoryRepository extends JpaRepository<CurrencyHistory,Integer> {
   // List<CurrencyHistory> findByCurrencyIdOrderByDateDesc(Integer currencyId);
    List<CurrencyHistory> findCurrencyHistoriesByCurrencyCurrencyId(Integer CurrencyId);


}
