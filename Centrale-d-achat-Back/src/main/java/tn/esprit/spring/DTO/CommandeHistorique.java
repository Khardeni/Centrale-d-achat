package tn.esprit.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entities.Article;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommandeHistorique {
    private Integer lignePanierId;  // for
    private Integer status;         // fedi
    private String nomArticle;
    private Double prixArticle;
    private Integer quantite;
    private Date dateAdded;
    private String marque;
    private String categorie;
    private Integer likeCount;
    private String image;
}
