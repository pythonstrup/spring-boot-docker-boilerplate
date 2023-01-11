package com.pythonstrup.demo.domain.auth.service;

import com.pythonstrup.demo.domain.auth.dto.response.SignupResponse;
import com.pythonstrup.demo.domain.auth.dto.service.SignupDTO;
import com.pythonstrup.demo.domain.user.entity.Role;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.RoleNotFoundException;
import com.pythonstrup.demo.domain.user.repository.RoleRepository;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @InjectMocks
    AuthService authService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Nested
    @DisplayName("회원가입")
    class SignUp {
        //given
        String username = "test";
        String password = "1234";
        SignupDTO dto = SignupDTO.builder().username(username).password(password).build();
        String encodedPassword = "aaabbbccc";
        Role roles = Role.builder().name("USER").build();

        @Nested
        @DisplayName("회원가입 성공")
        class SuccessCase {
            User savedUser = User.builder().username(username).password(password).roles(List.of(roles)).build();

            @Test
            @DisplayName("유저를 회원가입한다")
            void signup() {
                //when
                when(passwordEncoder.encode(dto.getPassword())).thenReturn(encodedPassword);
                when(roleRepository.findById(2L)).thenReturn(Optional.of(roles));
                when(userRepository.save(any())).thenReturn(savedUser);

                //then
                SignupResponse response = authService.signup(dto);
                assertEquals(response.getUsername(), username);
                assertEquals(response.getRoles().get(0), "USER");
            }
        }

        @Nested
        @DisplayName("회원가입 실패")
        class FailCase {
            @Test
            @DisplayName("잘못된 ID로 역할을 찾으면 RoleNotFoundException이 발생한다.")
            void roleNotFoundException(){
                //when
                when(passwordEncoder.encode(dto.getPassword())).thenReturn(encodedPassword);
                when(roleRepository.findById(2L)).thenThrow(new RoleNotFoundException());

                //then
                assertThrows(RoleNotFoundException.class, ()-> authService.signup(dto));
            }
        }
    }

}