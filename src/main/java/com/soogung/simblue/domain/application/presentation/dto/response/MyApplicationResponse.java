package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.Application;
import com.soogung.simblue.domain.application.domain.ReplyBlock;
import com.soogung.simblue.domain.application.domain.type.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MyApplicationResponse {

    private Long applicationId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String emoji;
    private Status status;
    private Integer numberOfReplies;
    private Long replyId;
    private LocalDate repliedAt;

    public static MyApplicationResponse of(Application application, Integer numberOfReplies) {
        return MyApplicationResponse.builder()
                .applicationId(application.getId())
                .title(application.getTitle())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .status(application.getStatus())
                .numberOfReplies(numberOfReplies)
                .build();
    }

    public static MyApplicationResponse of(ReplyBlock replyBlock) {
        Application application = replyBlock.getApplication();
        return MyApplicationResponse.builder()
                .applicationId(application.getId())
                .title(application.getTitle())
                .startDate(application.getStartDate())
                .endDate(application.getEndDate())
                .emoji(application.getEmoji())
                .status(application.getStatus())
                .repliedAt(LocalDate.from(replyBlock.getCreatedAt()))
                .replyId(replyBlock.getId())
                .build();
    }
}
