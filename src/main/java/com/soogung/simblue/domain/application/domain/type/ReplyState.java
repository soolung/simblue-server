package com.soogung.simblue.domain.application.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReplyState {

    APPROVED("수락됨"),
    REJECTED("거절됨"),
    WAITING("대기중"),
    ;

    private final String description;
}
