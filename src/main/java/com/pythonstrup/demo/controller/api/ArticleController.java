package com.pythonstrup.demo.controller.api;

import com.pythonstrup.demo.controller.dto.article.response.GetArticleResponse;
import com.pythonstrup.demo.controller.dto.article.response.PostArticleResponse;
import com.pythonstrup.demo.controller.dto.article.response.ResultGetArticleResponse;
import com.pythonstrup.demo.controller.dto.article.response.ResultPostArticleResposne;
import com.pythonstrup.demo.controller.dto.article.request.SaveArticleRequest;
import com.pythonstrup.demo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Article", description = "Article API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ArticleController {
    private final ArticleService articleService;

    @Operation(summary = "get article", description = "article 정보 조회")
    @GetMapping("/article")
    public ResponseEntity<ResultGetArticleResponse> get() {
        // 빌더 패턴 사용
        GetArticleResponse data = GetArticleResponse.builder()
                                            .id(1)
                                            .title("Hello")
                                            .content("Hello Contents")
                                            .build();
        ResultGetArticleResponse response = new ResultGetArticleResponse("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "save article", description = "article 저장")
    @PostMapping("/article")
    public ResponseEntity<ResultPostArticleResposne> save(@Parameter @RequestBody SaveArticleRequest saveArticleRequestDTO) {
        String id = articleService.save(saveArticleRequestDTO.toServiceDto()).getId();
        PostArticleResponse data = PostArticleResponse.builder().id(id).build();
        ResultPostArticleResposne response = new ResultPostArticleResposne("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
