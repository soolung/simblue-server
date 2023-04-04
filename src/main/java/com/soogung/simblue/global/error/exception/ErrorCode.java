package com.soogung.simblue.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements ErrorProperty {

    FORBIDDEN(403, "COMMON-403-1", "금지된 접근입니다"),
    USER_NOT_LOGIN(403,"USER-403-1", "로그인 되지 않은 유저입니다"),
    INVALID_TOKEN(403, "TOKEN-403-1", "유효하지 않은 토큰입니다"),
    EXPIRED_JWT(403, "TOKEN-403-2", "만료된 토큰입니다"),
    REFRESH_TOKEN_EXPIRED(403, "TOKEN-403-3", "만료된 리프레시 토큰입니다"),
    USER_NOT_FOUND(404, "USER-404-1", "유저를 찾을 수 없습니다"),
    BSM_AUTH_INVALID_CLIENT(500, "BSM-500-1", "Bsm 클라이언트가 유효하지 않습니다"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "서버 오류입니다");

    private final int status;
    private final String code;
    private final String message;
}
