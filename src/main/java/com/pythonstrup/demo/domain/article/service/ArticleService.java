package com.pythonstrup.demo.domain.article.service;

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

    public Article save(SaveArticleServiceDTO saveArticleServiceDTO) {
        Article article = new Article();
        article.setTitle(saveArticleServiceDTO.getTitle());
        article.setContent(saveArticleServiceDTO.getContents());
        article.setCreateAt(new Date());
        return articleRepository.save(article);
    }
}
