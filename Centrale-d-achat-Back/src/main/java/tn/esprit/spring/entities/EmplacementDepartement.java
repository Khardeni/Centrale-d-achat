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
public class EmplacementDepartement implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer emplacementDepartementId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Emplacement emplacementId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Department departementId;
}
