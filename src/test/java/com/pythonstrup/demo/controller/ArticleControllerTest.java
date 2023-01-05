package com.pythonstrup.demo.controller;

import com.pythonstrup.demo.controller.dto.article.response.ResultGetArticleResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ArticleControllerTest {

    @Autowired
    private ArticleController articleController;

    @Test
    void get_article_test() {
        // given

        // when
        ResponseEntity<ResultGetArticleResponse> response = articleController.get();

        // then
        String title = response.getBody().getData().getTitle();
        String content = response.getBody().getData().getContent();
        Assertions.assertEquals(title, "Hello");
        Assertions.assertEquals(content, "Hello Contents");
    }
}
