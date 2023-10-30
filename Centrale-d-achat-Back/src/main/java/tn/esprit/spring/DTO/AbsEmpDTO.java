package tn.esprit.spring.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Data
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbsEmpDTO {
    Long employeeId;
    Integer absenceId;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
    String absenceType;

}
