package com.pythonstrup.demo.domain.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pythonstrup.demo.common.service.DatabaseCleanup;
import com.pythonstrup.demo.domain.article.dto.request.SaveArticleRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DatabaseCleanup databaseCleanup;

    @AfterEach
    public void afterEach() { databaseCleanup.execute(); }

    @Nested
    @DisplayName("GET /v1/article - article 조회하기")
    class GetArticle {

        private final String url = "/v1/article";

        MultiValueMap<String, String> params;

        @BeforeEach
        public void setUp() {
            params = new LinkedMultiValueMap<>();
        }

        @Nested
        @DisplayName("성공 케이스")
        class SuccessCase {

            @Test
            @WithMockUser(username = "test1")
            @DisplayName("조회에 성공하면 200 코드와 article 정보를 전달한다.")
            void getArticleSuccess() throws Exception {
                //given

                //when
                ResultActions actions = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()));
                //then
                actions.andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());
            }
        }
    }

    @Nested
    @DisplayName("POST /v1/article - article 작성하기")
    class PostArticle {

        private final String url = "/v1/article";

        @Nested
        @DisplayName("성공 케이스")
        class SuccessCase {

            @Test
            @WithMockUser(username = "test2")
            @DisplayName("제목과 내용을 포함한 article 저장 시, 200 코드를 반환한다.")
            void postArticleSuccess() throws Exception {
                //given
                String object = objectMapper.writeValueAsString(SaveArticleRequest.builder()
                        .title("title")
                        .contents("content").build());
                //when
                ResultActions actions = mockMvc.perform(post(url)
                        .content(object)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()));
                //then
                actions.andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());
            }
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCase {

            @Test
            @WithMockUser(username = "test3")
            @DisplayName("모든 필드를 채우지 않고 article을 저장하면 400 Bad Request를 반환한다.")
            void postArticleFail() throws Exception {
                //given
                String object = objectMapper.writeValueAsString(SaveArticleRequest.builder()
                        .contents("content").build());
                //when
                ResultActions actions = mockMvc.perform(post(url)
                        .content(object)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()));
                //then
                actions.andExpect(status().isBadRequest())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());
            }
        }
    }
}
