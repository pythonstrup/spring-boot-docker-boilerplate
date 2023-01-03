package com.pythonstrup.demo.service;

import com.pythonstrup.demo.controller.dto.article.service.SaveArticleServiceDTO;
import com.pythonstrup.demo.entity.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
     void redis_save_test() {
        //given
        SaveArticleServiceDTO saveArticleServiceDTO = SaveArticleServiceDTO.builder()
                .title("First Article")
                .contents("This is first article.")
                .build();

        //when
        Article article = articleService.save(saveArticleServiceDTO);

        //then
        Assertions.assertThat(article.getTitle()).isEqualTo("First Article");
        Assertions.assertThat(article.getContent()).isEqualTo("This is first article.");
    }
}
