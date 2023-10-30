package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonLivreur implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer livraisonLivreurId;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Livraison livraison;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Livreur livreur;
}
