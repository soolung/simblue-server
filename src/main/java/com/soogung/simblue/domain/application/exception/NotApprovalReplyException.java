package com.soogung.simblue.domain.application.exception;

import com.soogung.simblue.domain.application.exception.error.ApplicationErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class NotApprovalReplyException extends SimblueException {

    public final static NotApprovalReplyException EXCEPTION = new NotApprovalReplyException();

    private NotApprovalReplyException() {
        super(ApplicationErrorProperty.NOT_APPROVAL_REPLY);
    }
}
