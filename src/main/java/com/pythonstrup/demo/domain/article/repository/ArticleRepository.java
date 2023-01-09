package com.pythonstrup.demo.domain.article.repository;

import com.pythonstrup.demo.domain.article.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {
}
