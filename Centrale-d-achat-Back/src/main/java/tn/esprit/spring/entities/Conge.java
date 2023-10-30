package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Data
public class Conge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer congeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String raison;
    private Boolean confirmation;
    private Boolean payer;
    private int SoldeConge;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Employee employeConge;
}
