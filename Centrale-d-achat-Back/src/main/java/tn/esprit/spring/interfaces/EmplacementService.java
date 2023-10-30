package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Emplacement;

import java.util.List;

public interface EmplacementService {
    List<Emplacement> getAllEmplacements();

    Emplacement getEmplacementById(int id);

    Emplacement addEmplacement(Emplacement emplacement);

    Emplacement updateEmplacement(int id, Emplacement emplacement);

    void deleteEmplacement(int id);

    void initEmplacement();
}
