package com.pythonstrup.demo.entity;

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

    private String title;

    private String content;

    private Date createAt;
}
