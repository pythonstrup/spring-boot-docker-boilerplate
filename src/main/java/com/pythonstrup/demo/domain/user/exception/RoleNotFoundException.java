package com.pythonstrup.demo.domain.user.exception;

import com.pythonstrup.demo.common.exceptions.CustomException;
import com.pythonstrup.demo.common.exceptions.ErrorCode;

public class RoleNotFoundException extends CustomException {
    public RoleNotFoundException() {
        super(ErrorCode.ROLE_NOT_FOUND);
    }
}
