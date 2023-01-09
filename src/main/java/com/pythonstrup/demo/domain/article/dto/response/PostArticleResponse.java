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
public class PostArticleResponse {

    @Schema(description = "Key", example = "1")
    private String id;
}
