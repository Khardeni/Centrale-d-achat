package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.core.util.Json;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategorieArticle implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer categorieArticleId;
    @JsonIgnore
    @OneToMany(mappedBy = "categorieArticle",cascade = CascadeType.REMOVE)
    private List<Article> articleList;
    private String nomCategorie;
    private String imageCategorie;


}
