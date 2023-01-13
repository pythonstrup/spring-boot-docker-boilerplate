package com.pythonstrup.demo.domain.log.service;

import com.pythonstrup.demo.domain.log.entity.Log;
import com.pythonstrup.demo.domain.log.repository.LogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {
    @InjectMocks
    LogService logService;

    @Mock
    LogRepository logRepository;

    @Nested
    @DisplayName("로그 저장하기")
    class SaveLog {
        private Long id;
        private String title;
        private String contents;
        private String username;

        @BeforeEach
        void setUp() {
            id = 1L;
            title = "Hello";
            contents = "Hello World";
            username = "pythonstrup";
        }

        @Nested
        @DisplayName("로그 저장을 성공한 케이스")
        class SuccessCase {
            @Test
            @DisplayName("유저네임과 제목, 내용을 넣은 로그의 저장을 성공적으로 해냈다.")
            void saveLog() {
                //given
                String logContents = "title: " + title + ", contents: " + contents + " 작성";
                Log logEntity = Log.builder().id(id).username(username).contents(logContents).build();


                // when
                when(logRepository.save(any())).thenReturn(logEntity);

                // then
                logService.saveLog(username, logContents);
            }
        }
    }
}