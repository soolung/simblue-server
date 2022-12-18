package com.soogung.simblue.global.security.jwt.exception;


import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.global.security.jwt.exception.error.JwtErrorProperty;

public class ExpiredTokenException extends SimblueException {

    public final static ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(JwtErrorProperty.EXPIRED_TOKEN);
    }
}
