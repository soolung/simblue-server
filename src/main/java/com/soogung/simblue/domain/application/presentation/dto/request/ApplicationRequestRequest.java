package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.ApplicationQuestion;
import com.soogung.simblue.domain.application.domain.ApplicationRequest;
import com.soogung.simblue.domain.application.domain.ApplicationRequestBlock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequestRequest {

    @NotNull
    private Long applicationQuestionId;

    @NotNull
    private List<String> answer;

    public ApplicationRequest toEntity(ApplicationQuestion question, ApplicationRequestBlock block, String a) {
        return ApplicationRequest.builder()
                .answer(a)
                .applicationQuestion(question)
                .applicationRequestBlock(block)
                .build();
    }
}
