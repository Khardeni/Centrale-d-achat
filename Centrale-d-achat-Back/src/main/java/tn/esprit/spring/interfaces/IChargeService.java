package tn.esprit.spring.interfaces;
import tn.esprit.spring.entities.Charge;

import java.util.List;
import java.util.Optional;

public interface IChargeService {
    Charge addCharge(Charge charge);

    List<Charge> findAllCharges();

    Optional<Charge> findChargeById(Integer id);

    Charge updateCharge(Integer chargeId, Charge updatedCharge);


    void deleteChargeById(Integer chargeId);

}
