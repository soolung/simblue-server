package com.soogung.simblue.infrastructure.s3.exception;

import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.infrastructure.s3.exception.error.S3ErrorProperty;

public class FailedToSaveException extends SimblueException {

    public final static FailedToSaveException EXCEPTION = new FailedToSaveException();

    private FailedToSaveException() {
        super(S3ErrorProperty.FAILED_TO_SAVE);
    }
}
