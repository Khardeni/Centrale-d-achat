package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Marque;
import tn.esprit.spring.interfaces.IMarqueService;
import tn.esprit.spring.repositories.MarqueRepository;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MarqueImpl implements IMarqueService {
    @Autowired
    MarqueRepository marqueRepository;
    @Override

    public void addMarque(Marque marque) {
        marqueRepository.save(marque);
    }

    @Override
    public void deleteMarque(Marque marque) {
     marqueRepository.delete(marque);
    }

    @Override
    public Marque updateMarque(Marque newmarque, Integer marqueId) {
        Marque marque = marqueRepository.findById(marqueId).orElse(null);
        marque.setNomMarque(newmarque.getNomMarque());
        return marqueRepository.save(marque);
    }

    @Override
    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }
    @Override
    public Marque uploadImage(MultipartFile file, int id) throws IOException {
        Marque marque = marqueRepository.findById(id).orElse(null);
        marque.setLogoMarque(file.getBytes());
        return marqueRepository.save(marque);
    }
    @Override
    public Marque getMarqueById(Integer id) {
        Marque marque = marqueRepository.findById(id).orElse(null);
        return marque;
    }

}
