package com.pythonstrup.demo.service;

import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.handler.exceptions.user.UsernameNotFoundException;
import com.pythonstrup.demo.repository.UserRepository;
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
