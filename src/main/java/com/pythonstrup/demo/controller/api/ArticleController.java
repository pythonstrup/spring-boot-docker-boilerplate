package com.pythonstrup.demo.controller.api;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.article.response.GetArticleResponseDTO;
import com.pythonstrup.demo.controller.dto.article.response.PostArticleResponseDTO;
import com.pythonstrup.demo.controller.dto.article.web.SaveArticleRequestDTO;
import com.pythonstrup.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article")
    public ResponseEntity<ResultDTO<GetArticleResponseDTO>> get() {
        // 빌더 패턴 사용
        GetArticleResponseDTO data = GetArticleResponseDTO.builder()
                                            .id(1)
                                            .title("Hello")
                                            .content("Hello Contents")
                                            .build();
        ResultDTO<GetArticleResponseDTO> response = ResultDTO.of("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<ResultDTO<PostArticleResponseDTO>> save(@RequestBody SaveArticleRequestDTO saveArticleRequestDTO) {
        String id = articleService.save(saveArticleRequestDTO.toServiceDto()).getId();
        PostArticleResponseDTO data = PostArticleResponseDTO.builder().id(id).build();
        ResultDTO<PostArticleResponseDTO> response = ResultDTO.of("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
