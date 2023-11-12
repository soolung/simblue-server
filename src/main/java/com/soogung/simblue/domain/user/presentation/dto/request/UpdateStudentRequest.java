package com.soogung.simblue.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpdateStudentRequest {

    @NotBlank
    private String studentNumber;

    @Min(2021)
    @NotNull
    private Integer admissionYear;
}
