package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LignePanier implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer lignePanierId;

    @JsonIgnore
    @ManyToOne
    private Panier panier;
    @JsonIgnore
    @ManyToOne
    private Article article;

    private Integer status;
    private Integer quantite;
    private Date dateAdded;
    @PreRemove
    public void updatePanierTotalPrice() {
        float articlePrice = article.getPrixHT();

        float lignePanierTotalPrice = articlePrice * quantite ;
        float currentPanierTotalPrice = panier.getCoutTotal();
        panier.setCoutTotal(currentPanierTotalPrice - lignePanierTotalPrice);
        panier.setNbrArticle(panier.getNbrArticle()-1);
    }
}
