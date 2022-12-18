package com.soogung.simblue.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class StudentRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String studentNumber;

    @NotNull
    private Integer admissionYear;
}
