package com.soogung.simblue.global.security.jwt.exception;

import com.soogung.simblue.global.error.exception.ErrorCode;
import com.soogung.simblue.global.error.exception.SimblueException;

public class RefreshTokenExpiredException extends SimblueException {

    public final static RefreshTokenExpiredException EXCEPTION = new RefreshTokenExpiredException(ErrorCode.REFRESH_TOKEN_EXPIRED);

    public RefreshTokenExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
