package com.pythonstrup.demo.domain.article.dto.response;

import com.pythonstrup.demo.common.dto.ResultDTO;

public class ResultPostArticleResposne extends ResultDTO<PostArticleResponse> {
    public ResultPostArticleResposne(String resultCode, String message, PostArticleResponse data) {
        super(resultCode, message, data);
    }
}
