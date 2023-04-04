package com.soogung.simblue.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SimblueException extends RuntimeException {

    private final ErrorCode errorCode;
}
