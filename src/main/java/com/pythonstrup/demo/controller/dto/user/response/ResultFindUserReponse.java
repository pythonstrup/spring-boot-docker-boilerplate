package com.pythonstrup.demo.controller.dto.user.response;

import com.pythonstrup.demo.common.dto.ResultOkDTO;

public class ResultFindUserReponse extends ResultOkDTO<FindUserResponse> {
    public ResultFindUserReponse(String resultCode, String message, FindUserResponse data) {
        super(resultCode, message, data);
    }
}
