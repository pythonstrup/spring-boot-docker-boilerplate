package com.pythonstrup.demo.domain.article.service;

import com.pythonstrup.demo.domain.article.dto.response.PostArticleResponse;
import com.pythonstrup.demo.domain.article.dto.service.SaveArticleServiceDTO;
import com.pythonstrup.demo.domain.article.entity.Article;
import com.pythonstrup.demo.domain.article.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Nested
    @DisplayName("Article 저장")
    class PostArticle {
        private String id;
        private String title;
        private String content;

        void setUp() {
            id = "1";
            title = "title";
            content = "content";}

        @Nested
        @DisplayName("정상적으로 Article이 저장된 케이스")
        class SuccessCase {


            @Test
            void postArticleTest() {
                //given
                Article article = Article.builder().id(id).title(title).contents(content).build();
                SaveArticleServiceDTO dto = SaveArticleServiceDTO.builder()
                        .title(title)
                        .contents(content)
                        .build();

                //when
                when(articleRepository.save(any())).thenReturn(article);
                
                //then
                PostArticleResponse data = articleService.save(dto);
                Assertions.assertEquals(data, PostArticleResponse.builder().id(id).build());


            }
        }

//        @Nested
//        @DisplayName("Article 저장에 실패한 케이스")
//        class FailCase {
//            @Test
//            void postArticleTest() {
//                //given
//
//                //when
//                when(articleRepository.save(null)).thenReturn(null);
//
//                //then
//                Assertions.assertThrows(CustomException.class, () -> {
//                    articleService.save(null);
//                });
//            }
//        }
    }

}