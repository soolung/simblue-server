package com.soogung.simblue.global.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public enum Operator {
    EQUAL((target, other) -> other.equals(target)),
    NOT_EQUAL((target, other) -> !other.equals(target)),
    GREATER_THAN((target, other) -> (Integer) other > (Integer) target),
    GREATER_THAN_OR_EQUAL((target, other) -> (Integer) other >= (Integer) target),
    LESS_THAN((target, other) -> (Integer) other < (Integer) target),
    LESS_THAN_OR_EQUAL((target, other) -> (Integer) other <= (Integer) target),
    BEFORE((target, other) -> (LocalDate.parse((String) target)).isBefore((LocalDate.parse((String) other)))),
    AFTER((target, other) -> (LocalDate.parse((String) target)).isAfter((LocalDate.parse((String) other)))),
    BEFORE_OR_EQUAL((target, other) -> !AFTER.comparator.execute(target, other)),
    AFTER_OR_EQUAL((target, other) -> !BEFORE.comparator.execute(target, other));

    private final Comparator<Object> comparator;
}
