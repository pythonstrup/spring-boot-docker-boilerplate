package com.pythonstrup.demo.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SignupResponse {

    @Schema(description = "Key", example = "1")
    private Long id;

    @Schema(description = "username", example = "닉네임")
    private String username;

    @Schema(description = "role", example = "USER")
    private List<String> roles;
}
