package com.pythonstrup.demo.handler.exceptions;


import com.pythonstrup.demo.utils.ExceptionMeassge;
import com.pythonstrup.demo.utils.ExceptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionStatus.PAGE_NOT_FOUND, ExceptionMeassge.PAGE_NOT_FOUND),

    USERNAME_BAD_REQUEST(HttpStatus.BAD_REQUEST, ExceptionStatus.USERNAME_BAD_REQUEST,
                         ExceptionMeassge.USERNAME_BAD_REQUEST),

    ;

    private final HttpStatus statusCode;

    private final String status;

    private final String message;
}
