package com.pythonstrup.demo.domain.auth.dto.response.result;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.domain.auth.dto.response.SignupResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResultSignupResponse extends ResultDTO<SignupResponse> {
    public ResultSignupResponse(@NotBlank String status, @NotBlank String message, @NotNull SignupResponse data) {
        super(status, message, data);
    }
}
