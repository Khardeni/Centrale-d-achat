package tn.esprit.spring.DTO;

import lombok.*;
import tn.esprit.spring.entities.LignePanier;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class LignePanierDto {
    Integer idArticle;

    Integer quantite;

    Integer idPanier;

}
