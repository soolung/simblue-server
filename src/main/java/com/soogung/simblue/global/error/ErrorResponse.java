package com.soogung.simblue.global.error;

import com.soogung.simblue.global.error.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }
}
