package com.soogung.simblue.domain.application.presentation.dto.request;

import com.soogung.simblue.global.util.Operator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {

    @NotNull
    private Long questionId;

    @NotBlank
    private String target;

    @NotNull
    private Operator operator;
}
