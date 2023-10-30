package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Retour implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer retourId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Livraison livraisonId;

    private LocalDate dateAnnulation;
    private LocalDate dateRetour;
}
