package com.soogung.simblue.infrastructure.s3.exception;

import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.infrastructure.s3.exception.error.S3ErrorProperty;

public class OverFileSizeLimitException extends SimblueException {

    public final static OverFileSizeLimitException EXCEPTION = new OverFileSizeLimitException();

    private OverFileSizeLimitException() {
        super(S3ErrorProperty.OVER_FILE_SIZE_LIMIT);
    }
}
