package com.pythonstrup.demo.domain.auth.service;

import com.pythonstrup.demo.domain.auth.dto.response.SignupResponse;
import com.pythonstrup.demo.domain.auth.dto.service.SignupDTO;
import com.pythonstrup.demo.domain.user.entity.Role;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.RoleNotFoundException;
import com.pythonstrup.demo.domain.user.repository.RoleRepository;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public SignupResponse signup(SignupDTO signupDTO) {
        String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
        Role role = roleRepository.findById(2L).
                orElseThrow(RoleNotFoundException::new);
        User user = User.of(signupDTO, encodedPassword, List.of(role));
        User result = userRepository.save(user);
        SignupResponse responseDto = SignupResponse.of(result);
        return responseDto;
    }
}
