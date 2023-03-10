package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class CanNotUpdateApplicationException extends SimblueException {

    public final static CanNotUpdateApplicationException EXCEPTION = new CanNotUpdateApplicationException();

    private CanNotUpdateApplicationException() {
        super(ApplicationErrorProperty.CAN_NOT_UPDATE_APPLICATION);
    }
}
