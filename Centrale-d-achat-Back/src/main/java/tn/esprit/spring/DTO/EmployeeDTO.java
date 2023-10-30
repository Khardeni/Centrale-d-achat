package tn.esprit.spring.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {
    private Long EmployeeId;
    private Double salary;
    private String jobTitle;
    private Double TotalWorkedHours;
    private String fullName;
}

