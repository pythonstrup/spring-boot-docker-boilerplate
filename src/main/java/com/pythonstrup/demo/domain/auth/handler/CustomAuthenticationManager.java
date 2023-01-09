package com.pythonstrup.demo.security.handler;

import com.pythonstrup.demo.security.dto.CustomUserDetails;
import com.pythonstrup.demo.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());

        String requestPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(requestPassword, user.getPassword())) {
            throw new BadCredentialsException("Not Found User");
        }

        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    }
}
