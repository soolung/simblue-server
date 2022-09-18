package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.global.error.exception.ErrorCode;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ApplicationNotFoundException extends SimblueException {

    public final static ApplicationNotFoundException EXCEPTION = new ApplicationNotFoundException();

    private ApplicationNotFoundException() {
        super(ErrorCode.APPLICATION_NOT_FOUND);
    }
}
