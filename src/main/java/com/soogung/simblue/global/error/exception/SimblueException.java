package com.soogung.simblue.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SimblueException extends RuntimeException {

    private final ErrorProperty errorProperty;
}