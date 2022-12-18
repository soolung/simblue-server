package com.soogung.simblue.domain.user.exception;

import com.soogung.simblue.domain.user.exception.error.UserErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class PasswordMismatchException extends SimblueException {

    public final static PasswordMismatchException EXCEPTION = new PasswordMismatchException();

    private PasswordMismatchException() {
        super(UserErrorProperty.PASSWORD_MISMATCH);
    }
}
