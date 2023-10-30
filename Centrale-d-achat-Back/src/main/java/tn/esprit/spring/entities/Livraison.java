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
public class Livraison implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer livraisonId;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Facture factureId;


    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Commande commandeId;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Emplacement emplacementId;

    private LocalDate dateEnvoi;
    private LocalDate dateLivraisonPrevu;
    private LocalDate dateLivraison;
    private Integer typeLivraison;
    private String adresseLivraison;
    private Integer etatLivraison;
}
