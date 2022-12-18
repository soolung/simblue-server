package com.soogung.simblue.domain.user.exception;

import com.soogung.simblue.domain.user.exception.error.UserErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class UserNotFoundException extends SimblueException {

    public final static UserNotFoundException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorProperty.USER_NOT_FOUND);
    }
}
