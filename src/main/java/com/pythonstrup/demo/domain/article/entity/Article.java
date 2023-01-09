package com.pythonstrup.demo.domain.article.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Getter
@Setter
@RedisHash("Article")
public class Article {
    @Id
    private String id;

    @NotNull
    @Min(1)
    private String title;

    @NotNull
    @Min(1)
    private String content;

    @NotNull
    private Date createAt;
}
