package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Offre implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer offreId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private AppelOffre appelOffreId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    private String descOffre;
    private LocalDate dateOffre;
    private Integer etatOffre;
    
}
