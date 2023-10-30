package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer factureId;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Commande commandeId;

    private LocalDate dateFacturation;
    private Float prixHT;
    private Float solde;
    private Float prixTTC;
    private Integer etatFacture;
}


///                 TRI / ANALYSE ARTICLE VENDUE / MAILING / API PAIEMENT EN LIGNE
//                  CONFIRMATION OTP / BILAN COMPTABLE POUR DUREE DETERMINEE (m3a achref)
