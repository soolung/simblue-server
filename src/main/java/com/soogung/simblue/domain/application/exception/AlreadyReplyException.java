package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class AlreadyReplyException extends SimblueException {

    public final static AlreadyReplyException EXCEPTION = new AlreadyReplyException();

    private AlreadyReplyException() {
        super(ApplicationErrorProperty.ALREADY_REPLY);
    }
}
