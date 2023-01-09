package com.pythonstrup.demo.controller;

import com.pythonstrup.demo.domain.user.dto.response.ResultFindUserReponse;
import com.pythonstrup.demo.domain.user.controller.UserController;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.UsernameNotFoundException;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Test
    void findUser_test() {
        // given
        User user = User.builder().id(1).username("hello").password("1234").build();
        userRepository.save(user);

        // when
        ResponseEntity<ResultFindUserReponse> response = userController.findUserById(1);

        // then
        int id = response.getBody().getData().getId();
        String username = response.getBody().getData().getUsername();
        Assertions.assertEquals(id, 1);
        Assertions.assertEquals(username, "hello");
    }

    @Test
    void username_not_found_test() {
        // given

        // when & then
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userController.findUserById(10000);
        });
    }
}
