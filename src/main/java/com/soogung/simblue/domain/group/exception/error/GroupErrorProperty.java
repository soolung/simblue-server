package com.soogung.simblue.domain.group.exception.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupErrorProperty implements ErrorProperty {

    GROUP_NOT_FOUND(404, "그룹이 없습니다.");

    private final int status;
    private final String message;
}