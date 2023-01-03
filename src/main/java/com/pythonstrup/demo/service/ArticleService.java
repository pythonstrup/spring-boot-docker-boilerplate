package com.pythonstrup.demo.service;

import com.pythonstrup.demo.controller.dto.article.service.SaveArticleServiceDTO;
import com.pythonstrup.demo.entity.Article;
import com.pythonstrup.demo.repository.ArticleRepository;
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
