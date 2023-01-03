package com.pythonstrup.demo.controller.dto.article.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetArticleResponseDTO {
    private int id;
    private String title;
    private String content;
}
