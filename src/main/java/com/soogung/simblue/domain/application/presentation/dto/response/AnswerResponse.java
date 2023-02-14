package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Answer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerResponse {

    private String answer;

    public static AnswerResponse of(Answer answer) {
        return AnswerResponse.builder()
                .answer(answer.getAnswer())
                .build();
    }
}
