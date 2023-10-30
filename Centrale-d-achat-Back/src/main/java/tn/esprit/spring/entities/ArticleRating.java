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

public class ArticleRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rating;

    @ManyToOne
    @JsonIgnore
    private Article article;

    @ManyToOne
    @JsonIgnore
    private User user;
    public ArticleRating(int rating, User user, Article article) {
        this.rating = rating;
        this.user = user;
        this.article = article;
    }




    // Constructors, getters, and setters
}

