package com.pythonstrup.demo.domain.article.service;

import com.pythonstrup.demo.domain.article.dto.response.PostArticleResponse;
import com.pythonstrup.demo.domain.article.dto.service.SaveArticleServiceDTO;
import com.pythonstrup.demo.domain.article.entity.Article;
import com.pythonstrup.demo.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public PostArticleResponse save(SaveArticleServiceDTO saveArticleServiceDTO) {
        Article article = Article.of(saveArticleServiceDTO);
        Article result = articleRepository.save(article);
        PostArticleResponse responseDto = PostArticleResponse.of(result);
        return responseDto;
    }
}
