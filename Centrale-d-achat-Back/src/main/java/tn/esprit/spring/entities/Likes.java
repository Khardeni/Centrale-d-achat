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
public class Likes implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer likeId;
    private Integer objectId; // sois commentaire sois publication
    private Integer typeLike; // 1 = publication, 2 = commentaire
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
