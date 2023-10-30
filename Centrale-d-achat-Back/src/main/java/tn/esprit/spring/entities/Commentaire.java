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
public class Commentaire implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer commentaireId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Publication publicationId;

    private String commentaire;
    private Long likes;
    private Long dislikes;

}
