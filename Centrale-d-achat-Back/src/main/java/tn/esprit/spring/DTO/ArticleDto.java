package tn.esprit.spring.DTO;

import lombok.*;
import tn.esprit.spring.entities.Article;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor

    public class ArticleDto {
        private Article article;
        private Integer marqueId;
        private Integer categorieId;

    }

