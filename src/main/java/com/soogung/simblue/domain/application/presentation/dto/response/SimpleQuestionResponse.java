package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.type.QuestionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleQuestionResponse {

    private Long id;
    private String question;
    private QuestionType type;

    public static SimpleQuestionResponse of(Question question) {
        return SimpleQuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .type(question.getType())
                .build();
    }
}
