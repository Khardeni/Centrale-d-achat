package tn.esprit.spring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Charge;
import tn.esprit.spring.interfaces.IChargeService;
import tn.esprit.spring.repositories.ChargeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChargeImpl implements IChargeService {
    @Autowired
    ChargeRepository chargeRepository;
    @Override
    public Charge addCharge(Charge c) {
        return chargeRepository.save(c);
    }
    @Override
    public List<Charge> findAllCharges() {
        return chargeRepository.findAll();
    }
    @Override
    public Optional<Charge> findChargeById(Integer id) {
        return chargeRepository.findById(id);
    }
    @Override
    public Charge updateCharge(Integer chargeId, Charge updatedCharge) {
        Charge charge = chargeRepository.findById(chargeId).orElse(null);
        if (charge == null) {
            throw new NotFoundException("Charge with id " + chargeId + " not found");
        }
        charge.setTitreCharge(updatedCharge.getTitreCharge());
        charge.setTypeCharge(updatedCharge.getTypeCharge());
        charge.setTauxCharge(updatedCharge.getTauxCharge());
        return chargeRepository.save(charge);
    }
    @Override
    public void deleteChargeById(Integer chargeId) {
        chargeRepository.deleteById(chargeId);

    }

}