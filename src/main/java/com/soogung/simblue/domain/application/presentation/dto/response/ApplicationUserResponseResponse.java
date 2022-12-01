package com.soogung.simblue.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ApplicationUserResponseResponse {
    private String name;
    private String studentNumber;
    private Map<Long, List<ApplicationRequestResponse>> answerList;
}
