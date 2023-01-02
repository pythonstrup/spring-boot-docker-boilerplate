package com.pythonstrup.demo.controller.api;

import com.pythonstrup.demo.controller.dto.article.GetArticleResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ArticleControllerTest {

    @Autowired
    private ArticleController articleController;


    @Test
    void get_article_test() {
        // given

        // when
        ResponseEntity<GetArticleResponseDTO> response = articleController.get();

        // then
        String title = response.getBody().getTitle();
        String content = response.getBody().getContent();
        Assertions.assertThat(title).isEqualTo("Hello");
        Assertions.assertThat(content).isEqualTo("Hello Contents");
    }
}
