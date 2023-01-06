package com.pythonstrup.demo.security.service;

import com.pythonstrup.demo.repository.UserRepository;
import com.pythonstrup.demo.security.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("Username Not Found");
        return user.get();
    }
}
