package com.soogung.simblue.domain.user.exception.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorProperty implements ErrorProperty {

    USER_ALREADY_EXISTS(422, "사용자가 이미 존재합니다."),
    USER_NOT_FOUND(404, "사용자가 없습니다."),
    NOT_SCHOOL_MEMBER(422, "부산소프트웨어마이스터고등학교 구성원만 가입할 수 있습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),
    AUTHORITY_MISMATCH(401, "권한이 없습니다."),
    ;

    private final int status;
    private final String message;
}
