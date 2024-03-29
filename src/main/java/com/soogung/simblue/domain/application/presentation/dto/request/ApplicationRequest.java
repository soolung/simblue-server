package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.type.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @Nullable
    @Size(max = 500)
    private String description;

    @Nullable
    private LocalDate startDate;

    @Nullable
    private LocalDate endDate;

    @NotNull
    @Size(min = 1, max = 10)
    private String emoji;

    @NotNull
    private Boolean isAlways;

    @Nullable
    private Integer maxReplyCount;

    @NotNull
    private Boolean allowsDuplication;

    @NotNull
    private Boolean allowsUpdatingReply;

    @NotNull
    private List<OwnerRequest> ownerList;

    @NotNull
    @Valid
    private List<QuestionRequest> questionList;

    public Application toEntity() {
        return Application.builder()
                .title(title)
                .description(description)
                .startDate(startDate != null ? startDate.atStartOfDay() : null)
                .endDate(endDate != null ? endDate.atStartOfDay() : null)
                .emoji(emoji)
                .maxReplyCount(maxReplyCount)
                .allowsDuplication(allowsDuplication)
                .allowsUpdatingReply(allowsUpdatingReply)
                .state(isAlways ? State.ALWAYS : State.OPENED)
                .build();
    }
}
