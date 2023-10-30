package tn.esprit.spring.DTO;

import lombok.*;
import tn.esprit.spring.entities.Facture;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaiementFrontDTO {
    Integer paiementId;
    Integer factureId;
    String clientName;
    Integer methodePaiement;
    Integer cardNumber;
    LocalDate expirationDate;
    Integer cvv;
    Integer etatPaiement;
    LocalDate datePaiement;

}
