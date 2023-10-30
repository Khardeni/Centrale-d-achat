package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssocCommandeArticle implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer assocId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Commande commandeId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Article articleId;

    private Integer quantite;


}
