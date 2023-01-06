package com.pythonstrup.demo.controller.dto.auth.response.result;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.auth.response.LoginSuccessResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResultLoginSuccessResponse extends ResultDTO<LoginSuccessResponse> {
    public ResultLoginSuccessResponse(@NotBlank String status, @NotBlank String message, @NotNull LoginSuccessResponse data) {
        super(status, message, data);
    }
}
