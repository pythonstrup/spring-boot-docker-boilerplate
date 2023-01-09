package com.pythonstrup.demo.domain.article.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleValidationErrorSwagger {

    @Schema(description = "에러 명칭", example = "BAD_REQUEST")
    private String status;

    @Schema(description = "상세 메시지", example = "제목에 빈 값을 입력할 수 없습니다.")
    private String message;

    @Schema(description = "에러 목록",
            example = "[\"제목에 빈 값을 입력할 수 없습니다.\", \"게시글 내용에 빈 값을 넣을 수 없습니다.\"]",
            type = "array")
    private List<String> errors;
}
