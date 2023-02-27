package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
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
    private Long replyId;

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

    public static ApplicationResponse of(ReplyBlock replyBlock) {
        Application application = replyBlock.getApplication();

        return ApplicationResponse.builder()
                .id(application.getId())
                .title(application.getTitle())
                .description(application.getDescription())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .allowsDuplication(application.getAllowsDuplication())
                .status(application.getStatus())
                .replyId(replyBlock.getId())
                .build();
    }
}
