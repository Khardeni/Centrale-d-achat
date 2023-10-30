package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.AppelOffre;
import tn.esprit.spring.interfaces.IAppelOffre;
import tn.esprit.spring.repositories.AppelOffreRepository;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class AppelOffreImp implements IAppelOffre {

    AppelOffreRepository appelOffreRepository;
    @Override
    public void addAppelOffre(AppelOffre appelOffre) {
        appelOffre.setDescAppelOffre(appelOffre.getDescAppelOffre());
        appelOffre.setDatePublication(appelOffre.getDatePublication());
        appelOffre.setDateExpiration(appelOffre.getDateExpiration());
        appelOffre.setTitreAppelOffre(appelOffre.getTitreAppelOffre());
        appelOffre.setStatusAppelOffre(appelOffre.getStatusAppelOffre());
        appelOffreRepository.save(appelOffre);
    }

    @Override
    public AppelOffre editAppelOffre(AppelOffre appelOffre) {
        return appelOffreRepository.save(appelOffre);
    }


    @Override
    public String deleteAppelOffre(Integer id) {

        appelOffreRepository.deleteById(id);
        return "Entry Deleted";
    }

    @Override
    public List<AppelOffre> getAllAppelsOffre() {
        return appelOffreRepository.findAll();
    }

    @Override
    public AppelOffre getAppelOffreById(Integer id) {
        return appelOffreRepository.findById(id).get();
    }
}
