package com.pythonstrup.demo.domain.user.dto.response;

import com.pythonstrup.demo.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private Long id;

    @Schema(description = "유저 아이디", example = "pythonstrup")
    private String username;

    static public FindUserResponse of(User user) {
        return FindUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
