package com.pythonstrup.demo.handler.exceptions.user;

import com.pythonstrup.demo.handler.exceptions.CustomException;
import com.pythonstrup.demo.handler.exceptions.ErrorCode;

public class UsernameNotFoundException extends CustomException {
    public UsernameNotFoundException() {
        super(ErrorCode.USERNAME_NOT_FOUND);
    }
}
