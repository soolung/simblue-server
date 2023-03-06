package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.Owner;
import com.soogung.simblue.domain.application.domain.type.State;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ApplicationFormResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String emoji;
    private Boolean isAlways;
    private Boolean allowsDuplication;
    private Boolean allowsUpdatingReply;
    private List<QuestionResponse> questionList;
    private List<OwnerResponse> ownerList;

    public static ApplicationFormResponse of(Application application, List<Owner> ownerList) {
        return ApplicationFormResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .isAlways(application.getState() == State.ALWAYS)
                .allowsDuplication(application.getAllowsDuplication())
                .allowsUpdatingReply(application.getAllowsUpdatingReply())
                .questionList(
                        application.getQuestionList().stream()
                                .map(QuestionResponse::of)
                                .collect(Collectors.toList()))
                .ownerList(
                        ownerList.stream()
                                .map(Owner::getTeacher)
                                .map(OwnerResponse::of)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
