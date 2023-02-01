package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ApplicationHasNotStartedYetException extends SimblueException {

    public final static ApplicationHasNotStartedYetException EXCEPTION = new ApplicationHasNotStartedYetException();

    private ApplicationHasNotStartedYetException() {
        super(ApplicationErrorProperty.APPLICATION_HAS_NOT_STARTED_YET);
    }
}
