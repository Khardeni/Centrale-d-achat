package tn.esprit.spring.interfaces;


import tn.esprit.spring.DTO.CheckStockDTO;
import tn.esprit.spring.DTO.LivraisonDetailleDTO;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Livraison;

import java.util.List;

public interface IDeliveryService {
    void addDelivery(CheckStockDTO checkStockDTO);
    public void EditDelivery (Livraison livraison, Integer livraisonId);
    public List<Livraison> getDeliveries();
    public List<Livraison> getDeliveriesByDate(String dateFrom, String dateTo);
    public Livraison getDeliveryById(Integer livraisonId);
    public void setDeliveryToShipping(Integer livraisonId);
    public void setDeliveryToShipped(Integer livraisonId);
    public Facture getFactureIdByDeliveryId(Integer livraisonId);
    public LivraisonDetailleDTO getDeliveryDetails(Integer livraisonId);
    public List<LivraisonDetailleDTO> getDeliveryHistory(String username);
    public Integer getPendingDeliveries();
    public Integer getLateDeliveries();
}

