package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.Retour;

import java.util.List;

public interface IRetourService {
    public void CancelDelivery (Integer livraisonId);
    public List<Retour> getCanceledDeliveries();
}

