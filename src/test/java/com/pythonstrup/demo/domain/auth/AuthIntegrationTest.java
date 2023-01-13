package com.pythonstrup.demo.domain.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pythonstrup.demo.common.service.DatabaseCleanup;
import com.pythonstrup.demo.domain.auth.dto.request.LoginRequest;
import com.pythonstrup.demo.domain.auth.dto.request.SignupRequest;
import com.pythonstrup.demo.domain.user.entity.Role;
import com.pythonstrup.demo.domain.user.entity.User;
import com.pythonstrup.demo.domain.user.exception.RoleNotFoundException;
import com.pythonstrup.demo.domain.user.repository.RoleRepository;
import com.pythonstrup.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DatabaseCleanup databaseCleanup;

    @AfterEach
    public void afterEach() {
        databaseCleanup.execute();
    }

    @Nested
    @DisplayName("POST /v1/auth/signup - 유저 회원가입")
    class PostSignup{
        String username = "test";
        String password = "12345678";
        String url = "/v1/auth/signup";

        @Nested
        class SuccessCase{
            @Test
            @DisplayName("회원가입을 한다.")
            void signup() throws Exception {
                //given
                SignupRequest request = SignupRequest.builder().username(username).password(password).build();
                //when
                ResultActions actions = mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)));
                //then
                //{"status":"OK","message":"","data":{"id":1,"username":"test","roles":["USER"]}}
                actions.andExpect(status().isOk())
                        .andExpect(jsonPath("$.status").value("OK"))
                        .andExpect(jsonPath("$.data.id").value("1"))
                        .andExpect(jsonPath("$.data.username").value("test"))
                        .andExpect(jsonPath("$.data.roles[0]").value("USER"))
                        .andDo(print());
            }
        }
        @Nested
        class FailCase{
            @Test
            @DisplayName("빈 password로 회원가입을 하면 400 오류를 반환한다")
            void blackId() throws Exception {
                //given
                SignupRequest request = SignupRequest.builder().username(username).password("").build();

                //when
                ResultActions actions = mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)));

                //then
                actions.andExpect(status().isBadRequest());
            }

            @Test
            @DisplayName("빈 ID로 회원가입을 하면 400 오류를 반환한다")
            void blackPassword() throws Exception {
                //given
                SignupRequest request = SignupRequest.builder().username("").password(password).build();

                //when
                ResultActions actions = mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)));

                //then
                actions.andExpect(status().isBadRequest());
            }
        }
    }

    @Nested
    @DisplayName("POST /v1/auth/login - 유저 로그인")
    class Login {
        private final String url = "/v1/auth/login";
        private String username;
        private String password;

        @BeforeEach
        void setUp() {
            username = "pythonstrup";
            password = passwordEncoder.encode("1234");
        }

        @Nested
        @DisplayName("로그인 성공")
        class SuccessCase {

            @Test
            @DisplayName("등록된 유저의 아이디와 패스워드와 동일한 로그인 요청이 들어오면 204 코드 NO_CONTENT를 반환한다.")
            void loginSuccess() throws Exception {
                // given
                Role role = roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException());
                User user = User.builder().id(1L).username(username).password(password).roles(List.of(role)).build();
                userRepository.save(user);
                String requestPassword = "1234";
                LoginRequest request = LoginRequest.builder().username(username).password(requestPassword).build();

                // when
                ResultActions actions = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

                // then
                actions.andExpect(status().isNoContent());
            }
        }

        @Nested
        @DisplayName("로그인 실패")
        class FailCase {

            @Test
            @DisplayName("아이디가 존재하지 않는다면 로그인에 실패한다.")
            void loginFailByUsername() throws Exception {
                // given
                Role role = roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException());
                User user = User.builder().id(1L).username(username).password(password).roles(List.of(role)).build();
                userRepository.save(user);
                String requestUsername = "hello";
                String requestPassword = "1234";
                LoginRequest request = LoginRequest.builder().username(requestUsername).password(requestPassword).build();

                // when
                ResultActions actions = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

                // then
                actions.andExpect(status().isUnauthorized());
            }

            @Test
            @DisplayName("패스워드가 틀린다면 로그인에 실패한다.")
            void loginFailByPassword() throws Exception {
                // given
                Role role = roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException());
                User user = User.builder().id(1L).username(username).password(password).roles(List.of(role)).build();
                userRepository.save(user);
                String requestUsername = username;
                String requestPassword = "123456";
                LoginRequest request = LoginRequest.builder().username(requestUsername).password(requestPassword).build();

                // when
                ResultActions actions = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

                // then
                actions.andExpect(status().isUnauthorized());
            }
        }
    }
}
