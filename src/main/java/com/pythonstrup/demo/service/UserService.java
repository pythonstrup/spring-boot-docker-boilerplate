package com.pythonstrup.demo.service;

import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.handler.exceptions.user.UsernameNotFoundException;
import com.pythonstrup.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByUserId(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException();
        }

        return user.get();
    }
}
