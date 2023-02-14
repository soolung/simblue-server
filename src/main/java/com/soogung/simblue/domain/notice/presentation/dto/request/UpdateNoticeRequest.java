package com.soogung.simblue.domain.notice.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNoticeRequest {

    @NotNull
    @Length(min = 2, max = 50)
    private String notice;
}
