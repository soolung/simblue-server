package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class QuestionIsRequiredException extends SimblueException {

    public final static QuestionIsRequiredException EXCEPTION = new QuestionIsRequiredException();

    private QuestionIsRequiredException() {
        super(ApplicationErrorProperty.QUESTION_IS_REQUIRED);
    }
}
