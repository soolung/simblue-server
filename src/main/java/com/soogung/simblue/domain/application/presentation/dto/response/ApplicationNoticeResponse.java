package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApplicationNoticeResponse {

    private String notice;
    private String author;
    private LocalDateTime createdAt;
    private Boolean isPinned;

    public static ApplicationNoticeResponse of(ApplicationNotice notice) {
        return ApplicationNoticeResponse.builder()
                .notice(notice.getNotice())
                .author(notice.getTeacher().getUser().getName())
                .createdAt(notice.getCreatedAt())
                .isPinned(notice.getIsPinned())
                .build();
    }
}
