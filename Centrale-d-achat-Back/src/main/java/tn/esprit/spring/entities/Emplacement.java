package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Emplacement implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer emplacementId;
    private String nomEmplacement;
    private String adresseEmplacement;
    private String gouvernorat;


    @Transient
    @JsonIgnore
    private static final Map<String, String> gouvernoratsTunisiens = new HashMap<>();

    static {
        gouvernoratsTunisiens.put("Tunis", "Tunis");
        gouvernoratsTunisiens.put("Ariana", "Ariana");
        gouvernoratsTunisiens.put("Ben Arous", "Ben Arous");
        gouvernoratsTunisiens.put("Manouba", "Manouba");
        gouvernoratsTunisiens.put("Nabeul", "Nabeul");
        gouvernoratsTunisiens.put("Zaghouan", "Zaghouan");
        gouvernoratsTunisiens.put("Bizerte", "Bizerte");
        gouvernoratsTunisiens.put("Béja", "Beja");
        gouvernoratsTunisiens.put("Jendouba", "Jendouba");
        gouvernoratsTunisiens.put("Le Kef", "Kef");
        gouvernoratsTunisiens.put("Gafsa", "Gafsa");
        gouvernoratsTunisiens.put("Sousse", "Sousse");
        gouvernoratsTunisiens.put("Monastir", "Monastir");
    }

    public void setGouvernorat(String gouvernorat) throws IllegalArgumentException {
        if (gouvernoratsTunisiens.containsValue(gouvernorat)) {
            this.gouvernorat = gouvernorat;
        } else {
            throw new IllegalArgumentException("Le gouvernorat doit etre .");
        }
    }
}
