package com.pythonstrup.demo.domain.article.dto.response;

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
    private Long id;

    @Schema(description = "title", example = "제목")
    private String title;

    @Schema(description = "contents", example = "내용")
    private String contents;
}
