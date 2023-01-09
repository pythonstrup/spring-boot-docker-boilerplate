package com.pythonstrup.demo.service;

import com.pythonstrup.demo.controller.dto.auth.service.SignupDTO;
import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void login() {

    }

    public User signup(SignupDTO signupDTO) {
        String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(encodedPassword)
                .build();
        User result = userRepository.save(user);
        return result;
    }
}
