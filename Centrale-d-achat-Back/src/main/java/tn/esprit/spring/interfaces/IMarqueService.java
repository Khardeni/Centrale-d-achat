package tn.esprit.spring.interfaces;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Marque;

import java.io.IOException;
import java.util.List;

public interface IMarqueService {
    public void addMarque(Marque marque);
    public void deleteMarque(Marque marque);
    public Marque updateMarque(Marque marque,Integer marqueId);
    public List<Marque> getAllMarques();

    Marque uploadImage(MultipartFile file, int id) throws IOException;

    Marque getMarqueById(Integer id);
}
