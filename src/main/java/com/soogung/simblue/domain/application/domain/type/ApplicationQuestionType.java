package com.soogung.simblue.domain.application.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationQuestionType {
    TEXT(false),
    TEXTAREA(false),
    LINK(false),
    RADIO(true),
    CHECKBOX(true),
    ;

    private final boolean hasAnswer;
}
