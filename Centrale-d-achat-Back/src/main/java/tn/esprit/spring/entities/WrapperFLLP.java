package tn.esprit.spring.entities;

import lombok.*;

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
