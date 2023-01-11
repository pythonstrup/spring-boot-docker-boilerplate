package com.pythonstrup.demo.domain.article.controller;

import com.pythonstrup.demo.domain.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {
    @InjectMocks
    ArticleController articleController;

    @Mock
    ArticleService articleService;

    @Nested
    @DisplayName("")
    class SetArticle {

    }



}