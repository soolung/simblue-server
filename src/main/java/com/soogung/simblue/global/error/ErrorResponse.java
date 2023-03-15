package com.soogung.simblue.global.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorProperty errorProperty) {
        this.status = errorProperty.getStatus();
        this.message = errorProperty.getMessage();
        this.code = errorProperty.name();
    }
}
