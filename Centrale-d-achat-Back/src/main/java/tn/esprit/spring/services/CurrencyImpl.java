package tn.esprit.spring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.CurrencyHistory;
import tn.esprit.spring.interfaces.ICurrencyService;
import tn.esprit.spring.repositories.CurrencyHistoryRepository;
import tn.esprit.spring.repositories.CurrencyRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CurrencyImpl implements ICurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    CurrencyHistoryRepository currencyHistoryRepository;

    @Override
    public Currency addCurrency(Currency c) {
        return currencyRepository.save(c);
    }

    @Override
    public List<Currency> findAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> findCurrencyById(Integer id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Currency updateCurrency(Integer currencyId, Currency updatedCurrenncy) {
        Currency currency = currencyRepository.findById(currencyId).orElse(null);
        if (currency == null) {
            throw new NotFoundException("currency with id " + currencyId + " not found");
        }
        currency.setName(updatedCurrenncy.getName());
        currency.setSymbol(updatedCurrenncy.getSymbol());
        currency.setRate(updatedCurrenncy.getRate());
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrencyById(Integer id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Currency activateCurrency(Integer idCurrency) {
        Currency currency = currencyRepository.findById(idCurrency).orElse(null);
        if (currency == null) {
            throw new NotFoundException("Currency with id " + idCurrency + " not found");
        }
        currency.setActive(true);
        return currencyRepository.save(currency);
    }

    @Override
    public Currency deactivateCurrency(Integer currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElse(null);
        if (currency == null) {
            throw new NotFoundException("Currency with id " + currencyId + " not found");
        }
        currency.setActive(false);
        return currencyRepository.save(currency);
    }



    @Override
    public Currency updateExchangeRate(Integer currencyId, Double conversion) {
        Currency currency = currencyRepository.findById(currencyId).orElse(null);
        if (currency == null) {
            throw new NotFoundException("Currency with id " + currencyId + " not found");
        }
        CurrencyHistory history = new CurrencyHistory();
        history.setChId(null);
        history.setDateUpdated(new Date());
        history.setExchangeRate(currency.getRate());
        history.setCurrency(currency);
        currencyHistoryRepository.save(history);
        log.info("test");
        currency.setRate(conversion);
        return currencyRepository.save(currency);

    }

    @Override
    public List<CurrencyHistory> getExchangeRateHistory(Integer currencyId) {
        return currencyHistoryRepository.findCurrencyHistoriesByCurrencyCurrencyId(currencyId);
    }
    @Override
    public Double getExchangeRate(BigDecimal Amount, String fromCurrency, String toCurrency) throws Exception {
        String url = "https://www.xe.com/currencyconverter/convert/?Amount="+ Amount +"&From=" + fromCurrency + "&To=" + toCurrency;
        Document doc = Jsoup.connect(url).get();
        Element rateElement = doc.select("p.result__BigRate-sc-1bsijpp-1.iGrAod").first();
        if (rateElement != null) {
            String rateStr = rateElement.text();
            String[] parts = rateStr.split(" ");
            Double rate = Double.parseDouble(parts[0]);
            return rate;
        } else {
            throw new Exception("Could not retrieve exchange rate for " + fromCurrency + " to " + toCurrency);
        }
    }



    }





