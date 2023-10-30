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
public class TypeArticle implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer typeArticleId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private CategorieArticle categorieArticleId;

    private String nomTypeArticle;


}
