package tn.esprit.spring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEmplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssocAE;
    private Integer  stockE;

    @JsonIgnore
    @ManyToOne
    private Emplacement emplacement;

    @JsonIgnore
    @ManyToOne
    private Article article;
}
