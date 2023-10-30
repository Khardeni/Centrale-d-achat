package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer articleId;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleRating> ratings;

    private Float promotion;



    @JsonIgnore
    @ManyToOne
    private Marque marqueId;
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignePanierList;


@JsonIgnore
@ManyToOne
@JoinColumn(name = "categorie_article_id")
private CategorieArticle categorieArticle;
    private String nomArticle;
    private Integer seuilStock;
    private Float prixHT;
 @Lob
    private byte[] image;
    private String barcode;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<ArticleLike> likes;
    private int likeCount;
    private String imagee;

}
