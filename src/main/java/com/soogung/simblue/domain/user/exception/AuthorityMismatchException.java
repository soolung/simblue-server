package com.soogung.simblue.domain.user.exception;

import com.soogung.simblue.domain.user.exception.error.UserErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class AuthorityMismatchException extends SimblueException {

    public final static AuthorityMismatchException EXCEPTION = new AuthorityMismatchException();

    private AuthorityMismatchException() {
        super(UserErrorProperty.AUTHORITY_MISMATCH);
    }
}
