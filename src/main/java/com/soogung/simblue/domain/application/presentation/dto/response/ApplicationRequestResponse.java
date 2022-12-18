package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.ApplicationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationRequestResponse {

    private Long questionId;
    private String answer;

    public static ApplicationRequestResponse of(ApplicationRequest request) {
        return ApplicationRequestResponse.builder()
                .questionId(request.getApplicationQuestion().getId())
                .answer(request.getAnswer())
                .build();
    }
}
