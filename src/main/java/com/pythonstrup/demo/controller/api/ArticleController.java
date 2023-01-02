package com.pythonstrup.demo.controller.api;

import com.pythonstrup.demo.controller.dto.article.GetArticleResponseDTO;
import com.pythonstrup.demo.controller.dto.article.PostArticleResponseDTO;
import com.pythonstrup.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article")
    public ResponseEntity<GetArticleResponseDTO> get() {
        // 빌더 패턴 사용
        GetArticleResponseDTO response = GetArticleResponseDTO.builder()
                                            .id(1)
                                            .title("Hello")
                                            .content("Hello Contents")
                                            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<PostArticleResponseDTO> save() {
        // 생성자 사용
        int id = articleService.save().getId();
        return new ResponseEntity<>(new PostArticleResponseDTO(id), HttpStatus.OK);
    }
}
