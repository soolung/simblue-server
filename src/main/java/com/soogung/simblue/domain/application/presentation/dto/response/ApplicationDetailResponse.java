package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ApplicationDetailResponse {

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String emoji;
    private Boolean isAlways;
    private List<ApplicationQuestionResponse> applicationQuestionResponses;
    private List<ApplicationNoticeResponse> applicationNotices;

    public static ApplicationDetailResponse of(Application application) {
        return ApplicationDetailResponse.builder()
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .isAlways(application.getIsAlways())
                .applicationQuestionResponses(
                        application.getApplicationQuestions().stream()
                                .map(ApplicationQuestionResponse::of)
                                .collect(Collectors.toList()))
                .applicationNotices(
                        application.getApplicationNotices().stream()
                                .map(ApplicationNoticeResponse::of)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
