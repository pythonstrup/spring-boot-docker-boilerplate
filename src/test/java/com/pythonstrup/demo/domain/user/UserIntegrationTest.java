package com.pythonstrup.demo.domain.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pythonstrup.demo.common.service.DatabaseCleanup;
import com.pythonstrup.demo.common.utils.message.ExceptionMessage;
import com.pythonstrup.demo.common.utils.message.ExceptionStatus;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DatabaseCleanup databaseCleanup;

    @Nested
    @DisplayName("GET v1/user?userId={userId} - 유저 아이디로 유저 조회하기")
    @Transactional
    class FindByUserId {

        private final String url = "/v1/user";

        MultiValueMap<String, String> params;

        @BeforeEach
        public void setUp() {
            params = new LinkedMultiValueMap<>();
        }

        @AfterEach
        public void afterEach() {
            databaseCleanup.execute();
        }

        @Nested
        @DisplayName("성공 케이스")
        class SuccessCase {

            @Test
            @DisplayName("존재하는 유저의 이름을 조회하면 200 OK 코드, 유저이름과 아이디를 전달한다.")
            void findByUserIdSuccess() throws Exception {
                //given
                Role role = roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException());
                User user = User.builder().id(1L).username("pythonstrup").password("1234").roles(List.of(role)).build();
                userRepository.save(user);
                params.add("userId", "1");

                // when
                ResultActions actions = mockMvc.perform(get(url)
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON));

                // then
                actions.andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.status").value("OK"))
                        .andExpect(jsonPath("$.message").value(""))
                        .andExpect(jsonPath("$.data.id").value(1L))
                        .andExpect(jsonPath("$.data.username").value("pythonstrup"))
                        .andDo(print());
            }
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCase {

            @Test
            @DisplayName("존재하지 않는 유저를 찾으면 404 Not Found 를 반환한다.")
            void findByUserIdThatNotExist() throws Exception {
                // given
                params.add("userId", "10000");

                // when
                ResultActions actions = mockMvc.perform(get(url)
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON));

                // then
                actions.andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.status").value(ExceptionStatus.USER_NOT_FOUND))
                        .andExpect(jsonPath("$.message").value(ExceptionMessage.USER_NOT_FOUND))
                        .andDo(print());
            }
        }
    }
}
