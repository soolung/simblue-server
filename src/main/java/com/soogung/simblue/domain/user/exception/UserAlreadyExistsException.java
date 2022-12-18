package com.soogung.simblue.domain.user.exception;

import com.soogung.simblue.domain.user.exception.error.UserErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class UserAlreadyExistsException extends SimblueException {

    public final static UserAlreadyExistsException EXCEPTION = new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(UserErrorProperty.USER_ALREADY_EXISTS);
    }
}
