package com.pythonstrup.demo.controller.dto.article.response;

import com.pythonstrup.demo.common.dto.ResultOkDTO;

public class ResultGetArticleResponse extends ResultOkDTO<GetArticleResponse> {
    public ResultGetArticleResponse(String resultCode, String message, GetArticleResponse data) {
        super(resultCode, message, data);
    }
}
