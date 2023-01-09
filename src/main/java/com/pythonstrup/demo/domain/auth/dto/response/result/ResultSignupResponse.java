package com.pythonstrup.demo.controller.dto.auth.response.result;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.auth.response.SignupResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResultSignupResponse extends ResultDTO<SignupResponse> {
    public ResultSignupResponse(@NotBlank String status, @NotBlank String message, @NotNull SignupResponse data) {
        super(status, message, data);
    }
}
