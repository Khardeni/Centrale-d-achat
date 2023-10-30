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
public class AssocArticleImpot implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer assocArticleImpotId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Article articleId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Impot impotId;
}