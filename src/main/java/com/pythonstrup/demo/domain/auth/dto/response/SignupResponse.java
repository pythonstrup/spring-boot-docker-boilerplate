package com.pythonstrup.demo.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignupResponse {

    @Schema(description = "Key", example = "1")
    private int id;

    @Schema(description = "username", example = "닉네임")
    private String username;
}
