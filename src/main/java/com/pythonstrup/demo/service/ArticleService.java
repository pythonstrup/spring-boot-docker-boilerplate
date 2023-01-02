package com.pythonstrup.demo.service;

import com.pythonstrup.demo.entity.Article;
import com.pythonstrup.demo.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save() {
        Article article = new Article();
        article.setTitle("First Article");
        article.setContent("This is first article.");
        article.setCreateAt(new Date());
        return articleRepository.save(article);
    }
}
