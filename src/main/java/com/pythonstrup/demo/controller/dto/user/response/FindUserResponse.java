package com.pythonstrup.demo.controller.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FindUserResponse {

    @Schema(description = "유저 Key", example = "1")
    @NotNull
    private int id;

    @Schema(description = "유저 아이디", example = "pythonstrup")
    @NotBlank
    @Size(min=4, max=20)
    private String username;
}
