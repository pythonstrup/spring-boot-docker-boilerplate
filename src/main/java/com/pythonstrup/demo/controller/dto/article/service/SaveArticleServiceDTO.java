package com.pythonstrup.demo.controller.dto.article.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SaveArticleServiceDTO {
    private String title;
    private String contents;
}
