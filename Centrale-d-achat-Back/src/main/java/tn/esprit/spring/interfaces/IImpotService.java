package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.*;

import java.util.List;
import java.util.Optional;

public interface IImpotService {
    public Impot addImpot(Impot impot);

    List<Impot> findAllImpots();

    Optional<Impot> findImpotById(Integer id);

    void deleteImpotById(Integer id);

    Impot deactivateImpot(Integer impotId);

    Impot updateImpot(Integer impotId, Impot updatedImpot);


}
