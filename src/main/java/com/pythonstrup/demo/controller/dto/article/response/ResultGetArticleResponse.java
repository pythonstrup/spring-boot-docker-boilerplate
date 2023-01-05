package com.pythonstrup.demo.controller.dto.article.response;

import com.pythonstrup.demo.common.dto.ResultDTO;

public class ResultGetArticleResponse extends ResultDTO<GetArticleResponse> {
    public ResultGetArticleResponse(String resultCode, String message, GetArticleResponse data) {
        super(resultCode, message, data);
    }
}
