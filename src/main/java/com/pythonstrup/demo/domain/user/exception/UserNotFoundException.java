package com.pythonstrup.demo.domain.user.exception;

import com.pythonstrup.demo.common.exceptions.CustomException;
import com.pythonstrup.demo.common.exceptions.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
