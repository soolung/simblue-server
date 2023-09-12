package com.soogung.simblue.global.util;

@FunctionalInterface
public interface Comparator<T> {
    boolean execute(T target, T other);
}
