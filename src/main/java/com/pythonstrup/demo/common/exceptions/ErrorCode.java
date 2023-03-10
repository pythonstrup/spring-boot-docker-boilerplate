package com.pythonstrup.demo.common.exceptions;


import com.pythonstrup.demo.common.utils.message.ExceptionMessage;
import com.pythonstrup.demo.common.utils.message.ExceptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionStatus.PAGE_NOT_FOUND, ExceptionMessage.PAGE_NOT_FOUND),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionStatus.USER_NOT_FOUND,
                         ExceptionMessage.USER_NOT_FOUND),

    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, ExceptionStatus.ROLE_NOT_FOUND, ExceptionMessage.ROLE_NOT_FOUND),

    ;

    private final HttpStatus statusCode;

    private final String status;

    private final String message;
}
