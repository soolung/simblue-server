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

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String emoji;
    private Boolean isAlways;
    private List<ApplicationQuestionResponse> questionList;
    private List<ApplicationNoticeResponse> noticeList;

    public static ApplicationDetailResponse of(Application application, List<ApplicationNoticeResponse> noticeList) {
        return ApplicationDetailResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .isAlways(application.getIsAlways())
                .questionList(
                        application.getQuestionList().stream()
                                .map(ApplicationQuestionResponse::of)
                                .collect(Collectors.toList()))
                .noticeList(noticeList)
                .build();
    }
}
