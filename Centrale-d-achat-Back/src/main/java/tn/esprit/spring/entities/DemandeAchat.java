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
public class DemandeAchat implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer demandeAchatId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    private String titreDA;
    private String descDA;
    private LocalDate datePublication;
    private LocalDate dateExpiration;
    private Integer statusDA;
}
