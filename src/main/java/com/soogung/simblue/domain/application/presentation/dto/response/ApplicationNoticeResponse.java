package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.application.domain.ApplicationNotice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApplicationNoticeResponse {

    private String notice;
    private String name;
    private LocalDateTime createdAt;

    public static ApplicationNoticeResponse of(ApplicationNotice notice) {
        return ApplicationNoticeResponse.builder()
                .notice(notice.getNotice())
                .name(notice.getTeacher().getUser().getName())
                .createdAt(notice.getCreatedAt())
                .build();
    }
}
