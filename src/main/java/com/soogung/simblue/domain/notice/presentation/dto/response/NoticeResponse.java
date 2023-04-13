package com.soogung.simblue.domain.notice.presentation.dto.response;

import com.soogung.simblue.domain.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponse {

    private Long id;
    private String notice;
    private String author;
    private LocalDateTime createdAt;
    private Boolean isPinned;

    public static NoticeResponse of(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .notice(notice.getNotice())
                .author(notice.getTeacher().getName())
                .createdAt(notice.getCreatedAt())
                .isPinned(notice.getIsPinned())
                .build();
    }
}
