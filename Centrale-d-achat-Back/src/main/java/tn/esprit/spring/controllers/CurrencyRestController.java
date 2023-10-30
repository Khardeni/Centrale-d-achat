package tn.esprit.spring.controllers;


//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.CurrencyHistory;
import tn.esprit.spring.interfaces.ICurrencyService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
// @AllArgsConstructor
@RequestMapping("/Currency")
public class CurrencyRestController {
    @Autowired
    ICurrencyService currencyService;

    ///POST

    @PostMapping("/add-Currency")                      ///tested
    @ResponseBody
    public Currency addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @PostMapping("/activate-Currency/{currencyId}")       ///tested
    public Currency activateCurrency(@PathVariable("currencyId") Integer currencyId) {
        return currencyService.activateCurrency(currencyId);
    }

    @PostMapping("/deactivate-Currency/{currencyId}")    ///tested
    public Currency deactivateCurrency(@PathVariable("currencyId") Integer currencyId) {
        return currencyService.deactivateCurrency(currencyId);
    }

    ///GET
    @GetMapping("/Get-All-Currencies")          ///tested
    public List<Currency> getAllCurrencies() {
        return currencyService.findAllCurrencies();
    }

    @GetMapping("/Get-Currency-ById/{id}")       ///tested
    public ResponseEntity<Currency> getCandidateById(@PathVariable Integer id) {
        Optional<Currency> currency = currencyService.findCurrencyById(id);
        if (currency.isPresent()) {
            return ResponseEntity.ok(currency.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Get-ExchangeRateHistory/{currencyId}/history")           ///tested
    public ResponseEntity<List<CurrencyHistory>> getExchangeRateHistory(@PathVariable Integer currencyId) {
        List<CurrencyHistory> history = currencyService.getExchangeRateHistory(currencyId);
        return ResponseEntity.ok(history);
    }

    ///PUT
    @PutMapping("/Update-Currency/{id}")       ///tested
    public ResponseEntity<Currency> updateCurrency(@PathVariable Integer id, @RequestBody Currency updatedCurrency) {
        Optional<Currency> existingCurrency = currencyService.findCurrencyById(id);
        if (existingCurrency.isPresent()) {
            updatedCurrency.setCurrencyId(id);
            return ResponseEntity.ok(currencyService.updateCurrency(id, updatedCurrency));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-ExchangeRate/{currencyId}/{conversion}")    ///tested

    public Currency updateExchangeRate(@PathVariable ("currencyId")Integer currencyId,@PathVariable("conversion") Double conversion) {
        return currencyService.updateExchangeRate(currencyId, conversion);
    }


    ///DELETE
    @DeleteMapping("/delete-Currency/{id}")                ///tested
    public ResponseEntity<Void> deleteCandidate(@PathVariable Integer id) {
        Optional<Currency> existingCurrency = currencyService.findCurrencyById(id);
        if (existingCurrency.isPresent()) {
            currencyService.deleteCurrencyById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ExchangeRate/{from}/{to}")
    public Double getExchangeRate(@RequestParam("Amount") Double amount, @PathVariable("from") String fromCurrency, @PathVariable("to") String toCurrency) throws Exception {
       return currencyService.getExchangeRate(BigDecimal.valueOf(amount),fromCurrency, toCurrency);
    }
}
    /*@GetMapping("/convert/{FromCurrency}/{ToCurrency}/{Amount}")
    public ResponseEntity<Double> convertCurrency(
            @PathVariable("FromCurrency") Integer fromCurrencyId,
            @PathVariable("ToCurrency") Integer toCurrencyId,
            @PathVariable("Amount") Double amount) {
        try {
            Double convertedAmount = currencyService.convertCurrency(fromCurrencyId, toCurrencyId, amount);
            System.out.println("1");
            return ResponseEntity.ok(convertedAmount);
        } catch (NotFoundException e) {
            System.out.println("2");
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            System.out.println("3");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/


