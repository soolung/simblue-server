package com.soogung.simblue.domain.banner.exception.error;

import com.soogung.simblue.global.error.exception.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BannerErrorProperty implements ErrorProperty {

    BANNER_NOT_FOUND(404, "배너가 없습니다."),
    ;

    private final int status;
    private final String message;
}
