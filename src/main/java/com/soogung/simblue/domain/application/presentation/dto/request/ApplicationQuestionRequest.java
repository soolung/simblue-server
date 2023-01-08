package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import com.soogung.simblue.domain.application.domain.type.ApplicationQuestionType;
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
public class ApplicationQuestionRequest {

    @NotNull
    @Size(min = 2, max = 20)
    private String question;

    @Nullable
    @Size(max = 50)
    private String description;

    @NotNull
    private ApplicationQuestionType type;

    @Nullable
    private List<ApplicationAnswerRequest> answerList;

    public ApplicationQuestion toEntity(Application application) {
        return ApplicationQuestion.builder()
                .question(question)
                .description(description)
                .type(type)
                .application(application)
                .build();
    }
}