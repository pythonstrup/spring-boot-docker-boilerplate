package com.pythonstrup.demo.domain.auth.service;

import com.pythonstrup.demo.domain.user.entity.Role;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    @Mock
    UserRepository userRepository;

    @Nested
    class LoadUserByUsername {
        //given
        String username = "test";
        String password = "12345678";

        @Nested
        class SuccessCase {
            @Test
            @DisplayName("유저 이름을 찾아 CustomUserDetails를 반환한다.")
            void loadUserByUsername() {
                //given
                Role roles = Role.builder().name("USER").build();
                User user = User.builder().id(1L).username(username).password(password).roles(List.of(roles)).build();

                //then
                when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

                //then
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                assertThat(userDetails.getUsername(), equalTo(username));
                assertThat(userDetails.getPassword(), equalTo(password));
            }
        }

        @Nested
        class FailCase {
            @Test
            @DisplayName("유저 이름으로 유저를 찾을 수 없으면 UsernameNotFoundException을 반환한다.")
            void usernameNotFoundException() {
                //then
                when(userRepository.findByUsername(username)).thenThrow(UsernameNotFoundException.class);

                //then
                assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(username));
            }
        }
    }
}