package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ApplicationHasAlreadyEndedException extends SimblueException {

    public final static ApplicationHasAlreadyEndedException EXCEPTION = new ApplicationHasAlreadyEndedException();

    private ApplicationHasAlreadyEndedException() {
        super(ApplicationErrorProperty.APPLICATION_HAS_ALREADY_ENDED);
    }
}
