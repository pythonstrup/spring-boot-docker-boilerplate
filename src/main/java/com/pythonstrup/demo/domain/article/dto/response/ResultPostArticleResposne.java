package com.pythonstrup.demo.controller.dto.article.response;

import com.pythonstrup.demo.common.dto.ResultDTO;

public class ResultPostArticleResposne extends ResultDTO<PostArticleResponse> {
    public ResultPostArticleResposne(String resultCode, String message, PostArticleResponse data) {
        super(resultCode, message, data);
    }
}
