package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.global.error.exception.ErrorCode;
import com.soogung.simblue.global.error.exception.SimblueException;

public class ApplicationQuestionNotFoundException extends SimblueException {

    public final static ApplicationQuestionNotFoundException EXCEPTION = new ApplicationQuestionNotFoundException();

    private ApplicationQuestionNotFoundException() {
        super(ErrorCode.APPLICATION_QUESTION_NOT_FOUND);
    }
}
