package com.pythonstrup.demo.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ErrorResponse {

    @Schema(description = "상태 메시지")
    @NotBlank
    private final String status;

    @Schema(description = "에러에 대한 상세 메시지")
    @NotBlank
    private final String message;

    private List<String> errors;
}
