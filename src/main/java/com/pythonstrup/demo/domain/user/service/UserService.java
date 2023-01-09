package com.pythonstrup.demo.domain.user.service;

import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.UsernameNotFoundException;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException());

        return user;
    }
}
