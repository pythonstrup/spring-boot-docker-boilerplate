package com.pythonstrup.demo.domain.user.dto.response;

import com.pythonstrup.demo.common.dto.ResultDTO;

public class ResultFindUserReponse extends ResultDTO<FindUserResponse> {
    public ResultFindUserReponse(String resultCode, String message, FindUserResponse data) {
        super(resultCode, message, data);
    }
}
