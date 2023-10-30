package tn.esprit.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.entities.Livraison;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckStockDTO {
    private List<LignePanier> lpList;
    private Livraison livraison;
    private Integer factureId;
    private Integer commandeId;
}
