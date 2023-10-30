package tn.esprit.spring.controllers;

import tn.esprit.spring.entities.Charge;
import tn.esprit.spring.interfaces.IChargeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Charge")
public class ChargeRestController {
    @Autowired
    IChargeService chargeService;

    @PostMapping("/add-Charge")                   ///tested
    @ResponseBody
    public Charge addCharge(@RequestBody Charge charge) {
        return chargeService.addCharge(charge);
    }
    @GetMapping("/Get-All-Charges")                 ///tested
    public List<Charge> getAllCharges() {
        return chargeService.findAllCharges();
    }
    @GetMapping("/Get-Charge-ById/{id}")                   ///tested
    public ResponseEntity<Charge> getCandidateById(@PathVariable Integer id) {
        Optional<Charge> charge = chargeService.findChargeById(id);
        if (charge.isPresent()) {
            return ResponseEntity.ok(charge.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/Update-Charge/{id}")                     ///tested
    public ResponseEntity<Charge> updateCandidate(@PathVariable Integer id, @RequestBody Charge updatedCharge) {
        Optional<Charge> existingCharge = chargeService.findChargeById(id);
        if (existingCharge.isPresent()) {
            updatedCharge.setChargeId(id);
            return ResponseEntity.ok(chargeService.updateCharge(id, updatedCharge));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    ///DELETE
    @DeleteMapping("/delete-Charge/{id}")                  ///tested
    public ResponseEntity<Void> deleteCandidate(@PathVariable Integer id) {
        Optional<Charge> existingCharge = chargeService.findChargeById(id);
        if (existingCharge.isPresent()) {
            chargeService.deleteChargeById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //   @DeleteMapping("/delete-Charge/{chargeId}")
    //   public void deleteCharge(Integer chargeId){
    //       chargeService.deleteChargeById(chargeId);
    //  }
    //  @PutMapping("/update-Charge/{chargeId}")
    // public Charge updateCharge(@PathVariable  Integer chargeId,@RequestBody Charge updatedCharge){
    //    return  chargeService.updateCharge(chargeId,updatedCharge);
    //  }

}

