package com.pythonstrup.demo.domain.user.service;

import com.pythonstrup.demo.domain.user.dto.response.FindUserResponse;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.UserNotFoundException;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public FindUserResponse findByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        FindUserResponse responseDto = FindUserResponse.of(user);
        return responseDto;
    }
}
