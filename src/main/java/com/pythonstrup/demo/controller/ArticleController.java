package com.pythonstrup.demo.controller;

import com.pythonstrup.demo.controller.dto.article.request.SaveArticleRequest;
import com.pythonstrup.demo.controller.dto.article.response.GetArticleResponse;
import com.pythonstrup.demo.controller.dto.article.response.PostArticleResponse;
import com.pythonstrup.demo.controller.dto.article.response.ResultGetArticleResponse;
import com.pythonstrup.demo.controller.dto.article.response.ResultPostArticleResposne;
import com.pythonstrup.demo.service.ArticleService;
import com.pythonstrup.demo.utils.swagger.article.ArticleValidationErrorSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ResultGetArticleResponse.class))}),
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(schema = @Schema(implementation = ResultPostArticleResposne.class))}),
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패",
                    content = {@Content(schema = @Schema(implementation = ArticleValidationErrorSwagger.class))})
    })
    @PostMapping("/article")
    public ResponseEntity<ResultPostArticleResposne> save(@Parameter @Valid @RequestBody SaveArticleRequest saveArticleRequest) {
        String id = articleService.save(saveArticleRequest.toServiceDto()).getId();
        PostArticleResponse data = PostArticleResponse.builder().id(id).build();
        ResultPostArticleResposne response = new ResultPostArticleResposne("OK", "", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
