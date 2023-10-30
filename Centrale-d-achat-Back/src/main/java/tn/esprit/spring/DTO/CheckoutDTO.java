package tn.esprit.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Article;
import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.entities.Livraison;
import tn.esprit.spring.entities.User;

import java.time.LocalDate;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private List<ArticleCommandeDTO> ArticleCommandeList;
    private Integer commandeId;
    private String nom_prenom;
    private String email;
    private String phone;
    private String adresseLivraison;
    private LocalDate datelivraisonPrevu;
    private Float prixTotal;
}
