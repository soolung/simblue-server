package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
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
    private Boolean isAlways;
    private Boolean allowsDuplication;
    private Long replyId;

    public static ApplicationResponse of(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .isAlways(application.getIsAlways())
                .allowsDuplication(application.getAllowsDuplication())
                .build();
    }

    public static ApplicationResponse of(ReplyBlock replyBlock) {
        Application application = replyBlock.getApplication();

        return ApplicationResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .isAlways(application.getIsAlways())
                .allowsDuplication(application.getAllowsDuplication())
                .replyId(replyBlock.getId())
                .build();
    }
}
