package com.pythonstrup.demo.domain.user.exception;

import com.pythonstrup.demo.common.exceptions.CustomException;
import com.pythonstrup.demo.common.exceptions.ErrorCode;

public class UsernameNotFoundException extends CustomException {
    public UsernameNotFoundException() {
        super(ErrorCode.USERNAME_NOT_FOUND);
    }
}
