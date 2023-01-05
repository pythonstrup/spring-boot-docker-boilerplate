package com.pythonstrup.demo.controller.dto.article.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private int id;

    @Schema(description = "title", example = "제목")
    @NotEmpty
    private String title;

    @Schema(description = "content", example = "내용")
    @NotEmpty
    private String content;
}
