package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ReplyNotFoundException extends SimblueException {

    public final static ReplyNotFoundException EXCEPTION = new ReplyNotFoundException();

    private ReplyNotFoundException() {
        super(ApplicationErrorProperty.REPLY_NOT_FOUND);
    }
}
