package com.soogung.simblue.domain.application.presentation.dto.response;

import com.soogung.simblue.domain.user.domain.type.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationStatusResponse {

    private HashMap<String, List<ApplicationResponse>> applicationMap;
    private Authority authority;
}
