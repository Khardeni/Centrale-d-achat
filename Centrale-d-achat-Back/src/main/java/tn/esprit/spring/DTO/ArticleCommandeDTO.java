package tn.esprit.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommandeDTO {
    private String nomArticle;
    private Float prixArticle;
    private Integer quantite;
}
