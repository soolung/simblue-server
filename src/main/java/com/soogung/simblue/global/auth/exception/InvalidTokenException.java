package com.soogung.simblue.global.auth.exception;

import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.global.auth.exception.error.JwtErrorProperty;

public class InvalidTokenException extends SimblueException {

    public final static InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(JwtErrorProperty.INVALID_TOKEN);
    }
}
