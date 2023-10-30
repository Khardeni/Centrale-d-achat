package tn.esprit.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FactureDetailDTO {
    private String nomPrenomClient;
    private LocalDate dateCommmande;
    private String adresseLivraison;
    private String telephoneReceptionLivraison;
    private Integer etatFacture;
}