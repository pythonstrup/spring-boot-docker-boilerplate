package com.pythonstrup.demo.common.exceptions;


import com.pythonstrup.demo.common.utils.message.ExceptionMeassge;
import com.pythonstrup.demo.common.utils.message.ExceptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionStatus.PAGE_NOT_FOUND, ExceptionMeassge.PAGE_NOT_FOUND),

    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionStatus.USERNAME_NOT_FOUND,
                         ExceptionMeassge.USERNAME_NOT_FOUND),

    ;

    private final HttpStatus statusCode;

    private final String status;

    private final String message;
}
