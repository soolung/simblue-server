package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import com.soogung.simblue.domain.application.domain.type.ApplicationQuestionType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ApplicationQuestionResponse {

    private String question;
    private ApplicationQuestionType type;
    private List<ApplicationAnswerResponse> applicationAnswers;

    public static ApplicationQuestionResponse of(ApplicationQuestion applicationQuestion) {
        return ApplicationQuestionResponse.builder()
                .question(applicationQuestion.getQuestion())
                .type(applicationQuestion.getType())
                .applicationAnswers(
                        applicationQuestion.getType().isHasAnswer() ?
                                applicationQuestion.getApplicationAnswers().stream()
                                        .map(ApplicationAnswerResponse::of)
                                        .collect(Collectors.toList()) : null)
                .build();
    }
}
