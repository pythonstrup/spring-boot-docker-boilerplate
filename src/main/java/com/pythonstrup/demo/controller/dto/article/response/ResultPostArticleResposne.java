package com.pythonstrup.demo.controller.dto.article.response;

import com.pythonstrup.demo.common.dto.ResultOkDTO;

public class ResultPostArticleResposne extends ResultOkDTO<PostArticleResponse> {
    public ResultPostArticleResposne(String resultCode, String message, PostArticleResponse data) {
        super(resultCode, message, data);
    }
}
