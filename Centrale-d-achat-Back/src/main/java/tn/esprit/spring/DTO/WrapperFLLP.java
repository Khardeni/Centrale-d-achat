package tn.esprit.spring.DTO;

import lombok.*;
import tn.esprit.spring.entities.Facture;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.entities.Paiement;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WrapperFLLP {
    private Livraison livraison;
    private Livreur livreur;
    private Facture facture;
    private Paiement paiement;
}
