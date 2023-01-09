package com.pythonstrup.demo.repository;

import com.pythonstrup.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {
}
