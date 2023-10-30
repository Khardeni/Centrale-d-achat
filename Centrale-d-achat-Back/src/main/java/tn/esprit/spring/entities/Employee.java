package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long EmployeeId;
    private Double salary;
    private String jobTitle;
    private Double TotalWorkedHours;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private EmplacementDepartement emplacementDepartement;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeA")
    List<Absence> absences;
    @JsonIgnore
    @ToString.Exclude

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeP")
    List<Performance> performances;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeConge")
    List<Conge> conges;



}