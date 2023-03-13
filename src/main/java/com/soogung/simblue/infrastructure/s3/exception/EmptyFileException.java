package com.soogung.simblue.infrastructure.s3.exception;

import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.infrastructure.s3.exception.error.S3ErrorProperty;

public class EmptyFileException extends SimblueException {

    public final static EmptyFileException EXCEPTION = new EmptyFileException();

    private EmptyFileException() {
        super(S3ErrorProperty.EMPTY_FILE);
    }
}
