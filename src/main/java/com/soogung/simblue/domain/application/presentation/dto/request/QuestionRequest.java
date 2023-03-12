package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Question;
import com.soogung.simblue.domain.application.domain.type.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    @NotNull
    @Size(min = 2, max = 20)
    private String question;

    @Nullable
    @Size(max = 50)
    private String description;

    @NotNull
    private Boolean isRequired;

    @NotNull
    private QuestionType type;

    @Nullable
    private List<AnswerRequest> answerList;

    public Question toEntity(Application application) {
        return Question.builder()
                .question(question)
                .description(description)
                .isRequired(isRequired)
                .type(type)
                .application(application)
                .build();
    }
}