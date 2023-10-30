package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer absenceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeA;



    private String absenceType;
    @Enumerated(EnumType.STRING)
    private Status status;


    private Boolean isAbsent;

}
