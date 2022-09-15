package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateApplicationRequest {

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    @Nullable
    private String description;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Size(min = 1, max = 1)
    private String emoji;

    @NotNull
    private Boolean isAlways;

    @NotNull
    private List<ApplicationQuestionRequest> applicationQuestions;

    public Application toEntity() {
        return Application.builder()
                .title(title)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .emoji(emoji)
                .isAlways(isAlways)
                .build();
    }
}
