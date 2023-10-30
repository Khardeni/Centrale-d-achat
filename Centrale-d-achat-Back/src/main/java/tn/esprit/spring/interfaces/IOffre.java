package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Offre;

import java.util.List;

public interface IOffre {

    public void addOffre(Offre offre);
    public Offre editOffre(Offre offre);

    public String deleteOffre(Integer id);

    public List<Offre> getAllOffres();

    public Offre getOffreById(Integer id);

}
