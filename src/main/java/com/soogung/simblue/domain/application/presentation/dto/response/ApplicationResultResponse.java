package com.soogung.simblue.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationResultResponse {
    private List<String> questionList;
    private List<ApplicationUserResponseResponse> userResponseList;
}
