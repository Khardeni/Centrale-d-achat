package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.CurrencyHistory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICurrencyService {

    public Currency addCurrency (Currency c);

   // CurrencyDto addCurrency(CurrencyDto currencyDto);
    List<Currency> findAllCurrencies();

    Optional<Currency> findCurrencyById(Integer id);

    Currency updateCurrency(Integer currencyId, Currency updatedCurrenncy);

    void deleteCurrencyById(Integer id);

    public Currency activateCurrency(Integer currencyId);
    public Currency deactivateCurrency(Integer currencyId);

  //  Currency updateExchangeRate(Integer currencyId, BigDecimal conversion);

    Currency updateExchangeRate(Integer currencyId, Double conversion);

    List<CurrencyHistory> getExchangeRateHistory(Integer currencyId);

    //BigDecimal getExchangeRate(FloatingDecimal Amount, String fromCurrency, String toCurrency) throws Exception;

 Double getExchangeRate(BigDecimal Amount, String fromCurrency, String toCurrency) throws Exception;

 // Double convertCurrency(Integer fromCurrencyId, Integer toCurrencyId, Double amount) throws IOException;

 // Double convertCurrency(Integer fromCurrencyId, Integer toCurrencyId, Double amount) throws IOException;
}
