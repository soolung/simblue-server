package com.soogung.simblue.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class TeacherRequest {

    @NotBlank
    private String password;
}
