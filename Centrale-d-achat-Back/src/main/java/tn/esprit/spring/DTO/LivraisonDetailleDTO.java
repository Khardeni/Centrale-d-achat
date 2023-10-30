package tn.esprit.spring.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.esprit.spring.entities.Commande;
import tn.esprit.spring.entities.Emplacement;
import tn.esprit.spring.entities.Facture;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonDetailleDTO {
    private Integer livraisonId;
    private Integer factureId;
    private Integer commandeId;
    private String emplacementOrigine;
    private LocalDate dateEnvoi;
    private LocalDate dateLivraisonPrevu;
    private LocalDate dateLivraison;
    private Integer typeLivraison;
    private String adresseLivraison;
    private String numTelReception;
    private Integer etatLivraison;
}
