package tn.esprit.spring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Impot;
import tn.esprit.spring.interfaces.IImpotService;
import tn.esprit.spring.repositories.ImpotRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ImpotImpl implements IImpotService {
    @Autowired
    ImpotRepository impotRepository;

    @Override
    public Impot addImpot(Impot impot) {
        return impotRepository.save(impot);
    }
    @Override
    public List<Impot> findAllImpots() {
        return impotRepository.findAll();
    }
    @Override
    public Optional<Impot> findImpotById(Integer id) {
        return impotRepository.findById(id);
    }
    @Override
    public void deleteImpotById(Integer id) {
        impotRepository.deleteById(id);
    }
    @Override
    public Impot deactivateImpot(Integer impotId) {
        Impot impot = impotRepository.findById(impotId).orElse(null);
        if (impot == null) {
            throw new NotFoundException("Currency with id " + impotId + " not found");
        }
        impot.setIsActive(false);
        return impotRepository.save(impot);
    }
    @Override

    public Impot updateImpot(Integer impotId, Impot updatedImpot) {
        Impot impot = impotRepository.findById(impotId).orElse(null);
        if (impot == null) {
            throw new NotFoundException("Currency with id " + impotId + " not found");
        }
        impot.setTitreImpot(updatedImpot.getTitreImpot());
        impot.setTauxImpot(updatedImpot.getTauxImpot());
        impot.setTypeImpot(updatedImpot.getTypeImpot());
        impot.setIsActive(updatedImpot.getIsActive());
        return impotRepository.save(impot);
    }

}
