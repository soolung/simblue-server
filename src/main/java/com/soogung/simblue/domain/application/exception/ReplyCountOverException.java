package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ReplyCountOverException extends SimblueException {

    public final static ReplyCountOverException EXCEPTION = new ReplyCountOverException();

    private ReplyCountOverException() {
        super(ApplicationErrorProperty.REPLY_COUNT_OVER);
    }
}
