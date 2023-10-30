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
public class Paiement implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Integer paiementId;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Facture factureId;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Currency currencyId;
    private LocalDate datePaiement;
    private Integer methodePaiement;
    private Integer cardNumber;
    private LocalDate expirationDate;
    private Integer cvv;
    private Integer etatPaiement;
}
