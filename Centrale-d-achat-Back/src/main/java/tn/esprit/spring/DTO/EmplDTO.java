package tn.esprit.spring.DTO;

import lombok.*;
import tn.esprit.spring.entities.Article;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EmplDTO {
    private Integer emplacementId;
    private String departementId;
    private Integer emplacementDepartementId;
    private Integer nbEmployee;
}

