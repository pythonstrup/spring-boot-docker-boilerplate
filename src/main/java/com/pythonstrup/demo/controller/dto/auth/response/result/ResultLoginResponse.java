package com.pythonstrup.demo.controller.dto.auth.response.result;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.auth.response.LoginResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResultLoginResponse extends ResultDTO<LoginResponse> {
    public ResultLoginResponse(@NotBlank String status, @NotBlank String message, @NotNull LoginResponse data) {
        super(status, message, data);
    }
}
