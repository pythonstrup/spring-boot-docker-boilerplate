package com.pythonstrup.demo.controller.api;

import com.pythonstrup.demo.common.dto.ResultDTO;
import com.pythonstrup.demo.controller.dto.user.FindUserResponseDTO;
import com.pythonstrup.demo.entity.User;
import com.pythonstrup.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
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
        ResponseEntity<ResultDTO<FindUserResponseDTO>> response = userController.findUser("hello");

        // then
        int id = response.getBody().getData().getId();
        String username = response.getBody().getData().getUsername();
        Assertions.assertThat(id).isEqualTo(1);
        Assertions.assertThat(username).isEqualTo("hello");
    }
}
