package com.soogung.simblue.domain.user.exception;

import com.soogung.simblue.domain.user.exception.error.UserErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class NotSchoolMemberException extends SimblueException {

    public final static NotSchoolMemberException EXCEPTION = new NotSchoolMemberException();

    private NotSchoolMemberException() {
        super(UserErrorProperty.NOT_SCHOOL_MEMBER);
    }
}
