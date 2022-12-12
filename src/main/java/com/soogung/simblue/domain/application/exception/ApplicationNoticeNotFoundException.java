package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ApplicationNoticeNotFoundException extends SimblueException {

    public final static ApplicationNoticeNotFoundException EXCEPTION = new ApplicationNoticeNotFoundException();

    private ApplicationNoticeNotFoundException() {
        super(ApplicationErrorProperty.APPLICATION_NOTICE_NOT_FOUND);
    }
}
