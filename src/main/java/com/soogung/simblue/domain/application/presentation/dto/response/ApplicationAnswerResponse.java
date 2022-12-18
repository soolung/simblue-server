package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.ApplicationAnswer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationAnswerResponse {

    private String answer;

    public static ApplicationAnswerResponse of(ApplicationAnswer applicationAnswer) {
        return ApplicationAnswerResponse.builder()
                .answer(applicationAnswer.getAnswer())
                .build();
    }
}
