package com.soogung.simblue.infrastructure.s3.exception;

import com.soogung.simblue.global.error.exception.SimblueException;
import com.soogung.simblue.infrastructure.s3.exception.error.S3ErrorProperty;

public class ImageSizeMismatchException extends SimblueException {

    public final static ImageSizeMismatchException EXCEPTION = new ImageSizeMismatchException();

    private ImageSizeMismatchException() {
        super(S3ErrorProperty.IMAGE_SIZE_MISMATCH);
    }
}
