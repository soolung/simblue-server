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
    APPLICATION_HAS_NOT_STARTED_YET(422, "아직 신청 기한이 아닙니다."),
    ALREADY_REPLY(422, "중복 응답할 수 없습니다."),
    REPLY_NOT_FOUND(404, "응답이 없습니다."),
    QUESTION_IS_REQUIRED(422, "필수 항목을 확인하세요."),
    ;

    private final int status;
    private final String message;
}
