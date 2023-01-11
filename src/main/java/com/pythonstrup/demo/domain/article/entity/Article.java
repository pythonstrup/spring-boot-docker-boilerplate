package com.pythonstrup.demo.domain.article.entity;

import com.pythonstrup.demo.domain.article.dto.service.SaveArticleServiceDTO;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Builder
@Getter
@Setter
@RedisHash("Article")
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    private String id;

    @NotNull
    @Min(1)
    private String title;

    @NotNull
    @Min(1)
    private String contents;

    @CreatedDate
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private Date createdAt;

    static public Article of(SaveArticleServiceDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .build();
    }
}
