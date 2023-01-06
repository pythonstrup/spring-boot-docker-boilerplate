package com.pythonstrup.demo.controller.dto.auth.response.result;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.auth.response.LoginFailureResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResultLoginFailureResponse extends ResultDTO<LoginFailureResponse> {
    public ResultLoginFailureResponse(@NotBlank String status, @NotBlank String message, @NotNull LoginFailureResponse data) {
        super(status, message, data);
    }
}
