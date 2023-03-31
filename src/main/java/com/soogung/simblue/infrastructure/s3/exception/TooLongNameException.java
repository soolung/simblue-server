package com.soogung.simblue.infrastructure.s3.exception;

import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.infrastructure.s3.exception.error.S3ErrorProperty;

public class TooLongNameException extends SimblueException {

    public final static TooLongNameException EXCEPTION = new TooLongNameException();

    private TooLongNameException() {
        super(S3ErrorProperty.TOO_LONG_NAME);
    }
}
