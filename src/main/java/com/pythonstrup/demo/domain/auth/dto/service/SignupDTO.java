package com.pythonstrup.demo.domain.auth.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupDTO {

    private String username;

    private String password;
}
