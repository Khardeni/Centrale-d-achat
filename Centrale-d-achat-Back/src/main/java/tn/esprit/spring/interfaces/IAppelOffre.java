package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.AppelOffre;

import java.util.List;

public interface IAppelOffre {

    public void addAppelOffre(AppelOffre appelOffre);
    public AppelOffre editAppelOffre(AppelOffre appelOffre);

    public String deleteAppelOffre(Integer id);

    public List<AppelOffre> getAllAppelsOffre();

    public AppelOffre getAppelOffreById(Integer id);
}
