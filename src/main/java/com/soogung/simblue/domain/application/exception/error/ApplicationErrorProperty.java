package com.soogung.simblue.domain.application.exception.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationErrorProperty implements ErrorProperty {

    APPLICATION_NOT_FOUND(404, "신청이 없습니다."),
    APPLICATION_NOTICE_NOT_FOUND(404, "공지사항이 없습니다."),
    APPLICATION_QUESTION_NOT_FOUND(404, "해당 질문이 없습니다."),
    APPLICATION_HAS_ALREADY_ENDED(422, "기한이 종료된 신청입니다."),
    ;

    private final int status;
    private final String message;
}
