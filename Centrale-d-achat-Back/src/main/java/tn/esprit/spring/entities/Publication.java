package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Publication implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer publicationId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Rubrique rubriqueId;

    private LocalDate datePublication;
    private Long likes;
    private Long dislikes;
    private String titrePublication;
    private String contenu;

}
