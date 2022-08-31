package com.soogung.simblue.global.error.exception;

import lombok.Getter;

@Getter
public class SimblueException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public SimblueException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public SimblueException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}