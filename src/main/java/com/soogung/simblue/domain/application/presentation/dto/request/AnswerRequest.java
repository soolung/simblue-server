package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Answer;
import com.soogung.simblue.domain.application.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {

    @NotNull
    @Size(min = 2, max = 150)
    private String answer;

    public Answer toEntity(Question question) {
        return Answer.builder()
                .answer(answer)
                .question(question)
                .build();
    }
}
