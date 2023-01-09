package com.pythonstrup.demo.domain.auth.dto.request;

import com.pythonstrup.demo.domain.auth.dto.service.SignupDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    @Size(min = 4, max = 20)
    @NotBlank
    private String username;

    @Size(min = 4, max = 20)
    @NotBlank
    private String password;

    public SignupDTO toServiceDto() {
        return SignupDTO.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
