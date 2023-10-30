package tn.esprit.spring.DTO;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

    public class ArticleRatingDto {

        @NotNull
        private Integer id;

        @NotNull
        @Min(1)
        @Max(5)
        private Integer rating;
        private String articleName;
        private String username;

    }

