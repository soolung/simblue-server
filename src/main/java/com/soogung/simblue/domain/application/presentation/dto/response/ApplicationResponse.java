package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.type.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ApplicationResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String emoji;
    private Boolean allowsDuplication;
    private Status status;

    public static ApplicationResponse of(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .allowsDuplication(application.getAllowsDuplication())
                .status(application.getStatus())
                .build();
    }
}
