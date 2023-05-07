package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ReplyAlreadyHandledException extends SimblueException {

    public final static ReplyAlreadyHandledException EXCEPTION = new ReplyAlreadyHandledException();

    private ReplyAlreadyHandledException() {
        super(ApplicationErrorProperty.REPLY_ALREADY_HANDLED);
    }
}
