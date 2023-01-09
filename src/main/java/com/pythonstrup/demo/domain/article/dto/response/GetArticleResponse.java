package com.pythonstrup.demo.controller.dto.article.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetArticleResponse {

    @Schema(description = "Key", example = "1")
    private int id;

    @Schema(description = "title", example = "제목")
    private String title;

    @Schema(description = "content", example = "내용")
    private String content;
}
