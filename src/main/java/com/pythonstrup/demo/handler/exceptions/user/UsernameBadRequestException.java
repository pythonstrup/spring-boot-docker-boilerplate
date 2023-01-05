package com.pythonstrup.demo.handler.exceptions.user;

import com.pythonstrup.demo.handler.exceptions.CustomException;
import com.pythonstrup.demo.handler.exceptions.ErrorCode;

public class UsernameBadRequestException extends CustomException {
    public UsernameBadRequestException() {
        super(ErrorCode.USERNAME_BAD_REQUEST);
    }
}
