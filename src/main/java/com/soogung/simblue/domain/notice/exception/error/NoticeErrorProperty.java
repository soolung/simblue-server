package com.soogung.simblue.domain.notice.exception.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeErrorProperty implements ErrorProperty {

    NOTICE_NOT_FOUND(404, "공지사항이 없습니다.")
    ;

    private final int status;
    private final String message;
}
