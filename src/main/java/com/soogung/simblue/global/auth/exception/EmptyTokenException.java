package com.soogung.simblue.global.auth.exception;

import com.soogung.simblue.global.auth.exception.error.JwtErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class EmptyTokenException extends SimblueException {

    public final static EmptyTokenException EXCEPTION = new EmptyTokenException();

    private EmptyTokenException() {
        super(JwtErrorProperty.EMPTY_TOKEN);
    }
}
