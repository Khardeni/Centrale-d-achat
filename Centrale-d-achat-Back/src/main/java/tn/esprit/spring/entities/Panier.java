package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Panier implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer panierId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;
    @JsonIgnore
    @OneToMany(mappedBy = "panier", cascade = CascadeType.REMOVE, orphanRemoval = true)

    private List<LignePanier> lignePanierList;                                                                                   //si quantite=0
    private Integer nbrArticle;
    private LocalDate dateUpdated;
    private Float coutTotal;
}
