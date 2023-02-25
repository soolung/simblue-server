package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class CanNotUpdateReplyException extends SimblueException {

    public final static CanNotUpdateReplyException EXCEPTION = new CanNotUpdateReplyException();

    private CanNotUpdateReplyException() {
        super(ApplicationErrorProperty.CAN_NOT_UPDATE_REPLY);
    }
}
