package com.pythonstrup.demo.controller.dto.article.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostArticleResponse {

    @Schema(description = "Key", example = "1")
    @NotNull
    private String id;
}
