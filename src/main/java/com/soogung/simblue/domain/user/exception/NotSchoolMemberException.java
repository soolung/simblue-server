package com.soogung.simblue.domain.user.exception;

import com.soogung.simblue.global.error.exception.ErrorCode;
import com.soogung.simblue.global.error.exception.SimblueException;

public class NotSchoolMemberException extends SimblueException {

    public final static NotSchoolMemberException EXCEPTION = new NotSchoolMemberException();

    private NotSchoolMemberException() {
        super(ErrorCode.NOT_SCHOOL_MEMBER);
    }
}
