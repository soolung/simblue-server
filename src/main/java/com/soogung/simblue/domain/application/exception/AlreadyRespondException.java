package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class AlreadyRespondException extends SimblueException {

    public final static AlreadyRespondException EXCEPTION = new AlreadyRespondException();

    private AlreadyRespondException() {
        super(ApplicationErrorProperty.ALREADY_RESPOND);
    }
}
