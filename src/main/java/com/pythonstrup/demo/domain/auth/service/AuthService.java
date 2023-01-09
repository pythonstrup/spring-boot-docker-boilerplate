package com.pythonstrup.demo.domain.auth.service;

import com.pythonstrup.demo.domain.auth.dto.service.SignupDTO;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
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
