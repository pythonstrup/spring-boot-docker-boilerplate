package com.pythonstrup.demo.service;

import com.pythonstrup.demo.entity.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
     void redis_save_test() {
        //given

        //when
        Article article = articleService.save();

        //then
        Assertions.assertThat(article.getTitle()).isEqualTo("First Article");
        Assertions.assertThat(article.getContent()).isEqualTo("This is first article.");
    }
}
