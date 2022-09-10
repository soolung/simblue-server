package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.ApplicationAnswer;
import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class ApplicationAnswerRequest {

    @NotNull
    @Size(min = 2, max = 150)
    private String answer;

    public ApplicationAnswer toEntity(ApplicationQuestion applicationQuestion) {
        return ApplicationAnswer.builder()
                .answer(answer)
                .applicationQuestion(applicationQuestion)
                .build();
    }
}
