package com.soogung.simblue.domain.notice.exception;

import com.soogung.simblue.domain.notice.exception.error.NoticeErrorProperty;
import com.soogung.simblue.global.error.exception.SimblueException;

public class NoticeNotFoundException extends SimblueException {

    public final static NoticeNotFoundException EXCEPTION = new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(NoticeErrorProperty.NOTICE_NOT_FOUND);
    }
}
