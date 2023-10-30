package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.DemandeAchat;

import java.util.List;

public interface IDemandeAchat {

    public void addDemandeAchat(DemandeAchat demandeAchat);

    public DemandeAchat editDemandeAchat(DemandeAchat demandeAchat);

    public String deleteDemandeAchat(Integer id);

    public List<DemandeAchat> getAllDemandesAchat();

    public DemandeAchat getDemandeAchatById(Integer id);

}
