package com.pythonstrup.demo.domain.log.service;

import com.pythonstrup.demo.domain.log.entity.Log;
import com.pythonstrup.demo.domain.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    public void saveLog(String username, String logContents) {

        Log logEntity = Log.builder()
                .username(username)
                .contents(logContents)
                .build();
        logRepository.save(logEntity);
    }
}
