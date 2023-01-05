package com.pythonstrup.demo.controller.dto.article.request;

import com.pythonstrup.demo.controller.dto.article.service.SaveArticleServiceDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveArticleRequest {

    @Schema(description = "게시글 제목", example = "오늘의 모임")
    @NotBlank(message = "제목에 빈 값을 입력할 수 없습니다.")
    private String title;

    @Schema(description = "게시글 내용", example = "오늘의 모임은 000입니다!!")
    @NotBlank(message = "게시글 내용에 빈 값을 넣을 수 없습니다.")
    private String contents;

    public SaveArticleServiceDTO toServiceDto() {
        return SaveArticleServiceDTO.builder()
                .title(this.title)
                .contents(this.contents)
                .build();

    }
}
