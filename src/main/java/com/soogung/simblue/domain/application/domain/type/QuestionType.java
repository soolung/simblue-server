package com.soogung.simblue.domain.application.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {
    TEXT(false),
    TEXTAREA(false),
    LINK(false),
    RADIO(true),
    CHECKBOX(true),
    PEOPLE(false),
    ;

    private final boolean hasAnswer;
}
