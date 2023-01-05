package com.pythonstrup.demo.handler.exceptions;

import com.pythonstrup.demo.common.dto.ErrorResponse;
import com.pythonstrup.demo.utils.ExceptionMeassge;
import com.pythonstrup.demo.utils.ExceptionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Custom exception
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customException(final CustomException e){
        ErrorResponse response = ErrorResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, e.getStatusCode());
    }

    // Custom Exception 에서 처리되지 않은 400
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handledException(final RuntimeException e){
        ErrorResponse response = ErrorResponse.builder()
                .status("RuntimeException")
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundPage() {
        ErrorResponse response = ErrorResponse.builder()
                .status(ExceptionStatus.PAGE_NOT_FOUND)
                .message(ExceptionMeassge.PAGE_NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandledException(final Exception e){
        log.error("Exception", e);
        ErrorResponse response = ErrorResponse.builder()
                .status("InternalException")
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
