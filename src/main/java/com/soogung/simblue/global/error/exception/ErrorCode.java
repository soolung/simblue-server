package com.soogung.simblue.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),

    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    USER_NOT_FOUND(404, "사용자가 없습니다."),
    NOT_SCHOOL_MEMBER(422, "부산소프트웨어마이스터고등학교 구성원만 가입할 수 있습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),

    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),

    APPLICATION_NOT_FOUND(404, "신청이 없습니다."),
    APPLICATION_NOTICE_NOT_FOUND(404, "공지사항이 없습니다."),
    APPLICATION_QUESTION_NOT_FOUND(404, "해당 질문이 없습니다."),
    ;

    private final int status;
    private final String message;
}