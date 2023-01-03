package com.pythonstrup.demo.controller.dto.article.web;

import com.pythonstrup.demo.controller.dto.article.service.SaveArticleServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveArticleRequestDTO {
    private String title;
    private String contents;

    public SaveArticleServiceDTO toServiceDto() {
        return SaveArticleServiceDTO.builder()
                .title(this.title)
                .contents(this.contents)
                .build();

    }
}
