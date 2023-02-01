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

    private Long id;
    private String question;
    private String description;
    private Boolean isRequired;
    private ApplicationQuestionType type;
    private List<ApplicationAnswerResponse> answerList;

    public static ApplicationQuestionResponse of(ApplicationQuestion applicationQuestion) {
        return ApplicationQuestionResponse.builder()
                .id(applicationQuestion.getId())
                .question(applicationQuestion.getQuestion())
                .description(applicationQuestion.getDescription())
                .isRequired(applicationQuestion.getIsRequired())
                .type(applicationQuestion.getType())
                .answerList(
                        applicationQuestion.getType().isHasAnswer() ?
                                applicationQuestion.getAnswerList().stream()
                                        .map(ApplicationAnswerResponse::of)
                                        .collect(Collectors.toList()) : null)
                .build();
    }
}
