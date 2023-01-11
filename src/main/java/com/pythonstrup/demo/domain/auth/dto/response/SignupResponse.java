package com.pythonstrup.demo.domain.auth.dto.response;

import com.pythonstrup.demo.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class SignupResponse {

    @Schema(description = "Key", example = "1")
    private Long id;

    @Schema(description = "username", example = "닉네임")
    private String username;

    @Schema(description = "role", example = "USER")
    private List<String> roles;

    public static SignupResponse of(User user){
        return SignupResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(value -> value.getName()).collect(Collectors.toList()))
                .build();
    }
}
