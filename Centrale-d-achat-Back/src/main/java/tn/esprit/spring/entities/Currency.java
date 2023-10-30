package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer currencyId;
    private String name;
    private String symbol;
    private Double Rate;
    private Boolean active;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "currency")
    List<CurrencyHistory> currencyHistories;

  /*  @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "currencyId")
    List<Paiement> paiements;*/
  /*@Override
  public String toString() {
      return "Currency [id=" + currencyId + ", from=" + From + ", to=" + To + " , conversion=" + conversion + ", conversionResult=" + conversionResult + "]";*/
  }


