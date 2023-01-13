package com.pythonstrup.demo.domain.user.controller;

import com.pythonstrup.demo.domain.user.dto.response.FindUserResponse;
import com.pythonstrup.demo.domain.user.dto.response.ResultFindUserReponse;
import com.pythonstrup.demo.domain.user.entity.Role;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.UserNotFoundException;
import com.pythonstrup.demo.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Nested
    @DisplayName("유저 아이디로 유저 찾기")
    class FindByUserId {
        private Long id;
        private String username;
        private String password;
        private Role role;

        @BeforeEach
        void setUp() {
            id = 1L;
            username = "pythonstrup";
            password = "1234";
            role = Role.builder().name("USER").build();
        }

        @Nested
        @DisplayName("존재하는 유저의 아이디를 찾아서 성공한 케이스")
        class SuccessCase {
            @Test
            @DisplayName("아이디 1을 넣어서 pythonstrup을 찾았다.")
            void findByUserIdTest() {
                //given
                User user = User.builder().id(id).username(username).password(password).roles(List.of(role)).build();
                FindUserResponse data = FindUserResponse.of(user);

                // when
                when(userService.findByUserId(id)).thenReturn(data);

                // then
                ResponseEntity<ResultFindUserReponse> response = userController.findByUserId(id);
                Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
                Assertions.assertEquals(response.getBody().getStatus(), "OK");
                Assertions.assertEquals(response.getBody().getMessage(), "");
                Assertions.assertEquals(response.getBody().getData().getId(), data.getId());
                Assertions.assertEquals(response.getBody().getData().getUsername(), data.getUsername());
            }
        }

        @Nested
        @DisplayName("유저 찾기에 실패한 케이스")
        class FailCase {
            @Test
            @DisplayName("없는 유저의 아이디로 유저를 찾으려고 하면 에러를 던진다.")
            void findByUserIdThatNotExist() {
                //given

                // when
                when(userService.findByUserId(id)).thenThrow(UserNotFoundException.class);

                // then
                Assertions.assertThrows(UserNotFoundException.class, () -> {
                    userController.findByUserId(id);
                });
            }
        }
    }
}