package com.soogung.simblue.infrastructure.s3.exception.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum S3ErrorProperty implements ErrorProperty {

    EMPTY_FILE(400, "파일이 비었습니다."),
    TOO_LONG_NAME(400, "파일 이름이 너무 깁니다"),
    FAILED_TO_SAVE(424, "저장에 실패했습니다."),
    ;

    private final int status;
    private final String message;
}
